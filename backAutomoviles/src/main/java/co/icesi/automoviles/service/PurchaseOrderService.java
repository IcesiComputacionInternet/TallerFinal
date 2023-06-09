package co.icesi.automoviles.service;

import co.icesi.automoviles.dto.PurchaseOrderCreateDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import co.icesi.automoviles.enums.PurchaseOrderStatus;
import co.icesi.automoviles.enums.RoleType;
import co.icesi.automoviles.error.exception.DetailBuilder;
import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.util.ShopExceptionBuilder;
import co.icesi.automoviles.mapper.EShopUserMapper;
import co.icesi.automoviles.mapper.PurchaseOrderMapper;
import co.icesi.automoviles.model.EShopUser;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.model.PurchaseOrder;
import co.icesi.automoviles.repository.EShopUserRepository;
import co.icesi.automoviles.repository.ItemRepository;
import co.icesi.automoviles.repository.PurchaseOrderRepository;
import co.icesi.automoviles.service.utils.SortUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final EShopUserMapper eShopUserMapper;
    private final EShopUserRepository eShopUserRepository;
    private final ItemRepository itemRepository;

    public PurchaseOrderShowDTO createPurchaseOrder(PurchaseOrderCreateDTO purchaseOrderCreateDTO, String loggedEShopUser, String role){
        checkPermissionsToCreateOrDelete(loggedEShopUser, role, purchaseOrderCreateDTO.getEShopUserUUID());
        EShopUser eShopUser = getEShopUser(purchaseOrderCreateDTO.getEShopUserUUID());
        List<Item> items = getItems(purchaseOrderCreateDTO.getItems());
        PurchaseOrder purchaseOrder = purchaseOrderMapper.fromPurchaseOrderCreateDTOToPurchaseOrder(purchaseOrderCreateDTO);
        purchaseOrder.setPurchaseOrderId(UUID.randomUUID());
        purchaseOrder.setItems(items);
        purchaseOrder.setEShopUser(eShopUser);
        purchaseOrder.setStatus(PurchaseOrderStatus.Processing_order.toString());
        purchaseOrder.setTotal(getTotalCost(items));
        PurchaseOrderShowDTO purchaseOrderShowDTO = purchaseOrderMapper.fromPurchaseOrderToPurchaseOrderShowDTO(purchaseOrderRepository.save(purchaseOrder));
        purchaseOrderShowDTO.setEShopUser(eShopUserMapper.fromEShopUserToEShopUserShowDTOForPurchaseOrder(eShopUser));
        return purchaseOrderShowDTO;
    }

    public PurchaseOrderShowDTO getPurchaseOrderShowDTOById(String purchaseOrderId, String loggedEShopUser, String role){
        PurchaseOrder purchaseOrder = getPurchaseOrderById(purchaseOrderId);
        checkPermissionsToGet(loggedEShopUser, role, purchaseOrder.getEShopUser().getEShopUserId().toString());
        PurchaseOrderShowDTO purchaseOrderShowDTO = purchaseOrderMapper.fromPurchaseOrderToPurchaseOrderShowDTO(purchaseOrderRepository.save(purchaseOrder));
        purchaseOrderShowDTO.setEShopUser(eShopUserMapper.fromEShopUserToEShopUserShowDTOForPurchaseOrder(purchaseOrder.getEShopUser()));
        return purchaseOrderShowDTO;
    }

    private PurchaseOrder getPurchaseOrderById(String purchaseOrderId){
        return purchaseOrderRepository.findById(UUID.fromString(purchaseOrderId)).orElseThrow(
                ShopExceptionBuilder.createShopException(
                        "purchase order with id: "+purchaseOrderId+ " not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "purchase order", "id ", purchaseOrderId)
                )
        );
    }

    private void checkPermissionsToCreateOrDelete(String loggedEShopUser, String role, String purchaseOrderOwner){
        boolean theUsersAreDifferent = !loggedEShopUser.equals(purchaseOrderOwner);
        boolean theRoleIsNotAdmin = !role.equals(RoleType.ADMIN.toString());
        if(theUsersAreDifferent && theRoleIsNotAdmin){
            throw ShopExceptionBuilder.createShopException(
                    "you only have access to your own purchase orders",
                    HttpStatus.FORBIDDEN,
                    new DetailBuilder(ErrorCode.ERR_403, "you only have access to your own purchase orders")
            ).get();
        }
    }

    private void checkPermissionsToGet(String loggedEShopUser, String role, String purchaseOrderOwner){
        boolean theUsersAreDifferent = !loggedEShopUser.equals(purchaseOrderOwner);
        boolean theRoleIsNeitherAdminNorShop = !role.equals(RoleType.ADMIN.toString()) && !role.equals(RoleType.SHOP.toString());
        if(theUsersAreDifferent && theRoleIsNeitherAdminNorShop){
            throw ShopExceptionBuilder.createShopException(
                    "you only have access to your own purchase orders",
                    HttpStatus.FORBIDDEN,
                    new DetailBuilder(ErrorCode.ERR_403, "you only have access to your own purchase orders")
            ).get();
        }
    }

    private EShopUser getEShopUser(String eShopUserUUID){
        return eShopUserRepository.findById(UUID.fromString(eShopUserUUID)).orElseThrow(
                ShopExceptionBuilder.createShopException(
                        "e-shop user with id: "+eShopUserUUID+ " not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "e-shop user", "id ", eShopUserUUID)
                )
        );
    }

    private long getTotalCost(List<Item> items){
        return items.stream().map(Item::getPrice).reduce(0L, Long::sum);
    }

    private Item getItem(String itemUUID){
        return itemRepository.findById(UUID.fromString(itemUUID)).orElseThrow(
                ShopExceptionBuilder.createShopException(
                        "item with the id: " + itemUUID + " not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "item", "id", itemUUID))
        );
    }

    private List<Item> getItems(List<String> items) {
        return items.stream().map(this::getItem).toList();
    }

    public void deletePurchaseOrder(String purchaseOrderId, String loggedEShopUser, String role){
        PurchaseOrder purchaseOrder = getPurchaseOrderById(purchaseOrderId);
        checkPermissionsToCreateOrDelete(loggedEShopUser, role, purchaseOrder.getPurchaseOrderId().toString());
        purchaseOrderRepository.delete(purchaseOrder);
    }

    public PurchaseOrderShowDTO updateStateOfPurchaseOrder(String purchaseOrderId, String newState){
        try{
            PurchaseOrderStatus purchaseOrderStatus = PurchaseOrderStatus.valueOf(newState);
            PurchaseOrder purchaseOrder = getPurchaseOrderById(purchaseOrderId);
            purchaseOrder.setStatus(purchaseOrderStatus.toString());
            return fromPurchaseOrderToShowDTO(purchaseOrderRepository.save(purchaseOrder));
        }catch (IllegalArgumentException illegalArgumentException){
            String possibleValues = Arrays.stream(PurchaseOrderStatus.values())
                    .map(Enum::name)
                    .reduce((e1, e2) -> e1 + ", " + e2)
                    .orElse("");
            throw ShopExceptionBuilder.createShopException(
                    "status: the allowed values are " + possibleValues,
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_400, "status", "the allowed values are " + possibleValues)
            ).get();
        }
    }

    private PurchaseOrderShowDTO fromPurchaseOrderToShowDTO(PurchaseOrder purchaseOrder){
        PurchaseOrderShowDTO purchaseOrderShowDTO = purchaseOrderMapper.fromPurchaseOrderToPurchaseOrderShowDTO(purchaseOrder);
        purchaseOrderShowDTO.setEShopUser(eShopUserMapper.fromEShopUserToEShopUserShowDTOForPurchaseOrder(purchaseOrder.getEShopUser()));
        return purchaseOrderShowDTO;
    }

    public Page<PurchaseOrderShowDTO> getAllPurchaseOrders(int page, int perPage, String sortBy, String sortDir, String loggedEShopUser, String role){
        Pageable pageable = SortUtil.sort(page, perPage, sortBy, sortDir);
        Page<PurchaseOrder> purchaseOrders;
        if(!role.equals(RoleType.ADMIN.toString()) && !role.equals(RoleType.SHOP.toString())){
            purchaseOrders = purchaseOrderRepository.getAllPurchaseOrdersById(UUID.fromString(loggedEShopUser), pageable);
        }else {
            purchaseOrders = purchaseOrderRepository.getAllPurchaseOrders(pageable);
        }

        Page<PurchaseOrderShowDTO> purchaseOrderShowDTOS = purchaseOrders.map(entity -> fromPurchaseOrderToShowDTO(entity));
        return purchaseOrderShowDTOS;
    }

    public PurchaseOrderShowDTO updatePurchaseOrder(String purchaseOrderId, PurchaseOrderCreateDTO purchaseOrderCreateDTO){
        PurchaseOrder purchaseOrderOriginal = getPurchaseOrderById(purchaseOrderId);
        EShopUser eShopUser = getEShopUser(purchaseOrderCreateDTO.getEShopUserUUID());
        List<Item> items = getItems(purchaseOrderCreateDTO.getItems());
        PurchaseOrder purchaseOrderUpdated = purchaseOrderMapper.fromPurchaseOrderCreateDTOToPurchaseOrder(purchaseOrderCreateDTO);
        purchaseOrderUpdated.setPurchaseOrderId(purchaseOrderOriginal.getPurchaseOrderId());
        purchaseOrderUpdated.setItems(items);
        purchaseOrderUpdated.setEShopUser(eShopUser);
        purchaseOrderUpdated.setStatus(purchaseOrderOriginal.getStatus());
        purchaseOrderUpdated.setTotal(getTotalCost(items));
        PurchaseOrderShowDTO purchaseOrderShowDTO = purchaseOrderMapper.fromPurchaseOrderToPurchaseOrderShowDTO(purchaseOrderRepository.save(purchaseOrderUpdated));
        purchaseOrderShowDTO.setEShopUser(eShopUserMapper.fromEShopUserToEShopUserShowDTOForPurchaseOrder(eShopUser));
        return purchaseOrderShowDTO;
    }
}
