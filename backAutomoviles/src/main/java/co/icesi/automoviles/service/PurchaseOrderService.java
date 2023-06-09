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
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.LongStream;

@Service
@AllArgsConstructor
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final EShopUserMapper eShopUserMapper;
    private final EShopUserRepository eShopUserRepository;
    private final ItemRepository itemRepository;

    public PurchaseOrderShowDTO createPurchaseOrder(PurchaseOrderCreateDTO purchaseOrderCreateDTO, String loggedEShopUser, String role){
        checkPermissionsToCreate(loggedEShopUser, role, purchaseOrderCreateDTO.getEShopUserUUID());
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
        checkPermissionsToGet(loggedEShopUser, role, purchaseOrder.getPurchaseOrderId().toString());
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

    private void checkPermissionsToCreate(String loggedEShopUser, String role, String purchaseOrderOwner){
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

}
