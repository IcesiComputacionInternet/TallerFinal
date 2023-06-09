package co.com.icesi.eShopBackEnd.service;

import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
import co.com.icesi.eShopBackEnd.dto.DeleteItemDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import co.com.icesi.eShopBackEnd.error.enums.ErrorCode;
import co.com.icesi.eShopBackEnd.error.util.ArgumentsExceptionBuilder;
import co.com.icesi.eShopBackEnd.error.util.DetailBuilder;
import co.com.icesi.eShopBackEnd.mapper.ItemMapper;
import co.com.icesi.eShopBackEnd.model.Item;
import co.com.icesi.eShopBackEnd.repository.CategoryRepository;
import co.com.icesi.eShopBackEnd.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;

    public ResponseItemDTO save(CreateItemDTO itemDTO){
        boolean exists = itemRepository.itemExists(itemDTO.name());
        if(exists){
            throw ArgumentsExceptionBuilder.createArgumentsException(
                    "Existing data",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_406,"Item name")
            );
            //throw new ArgumentsException("Item name already exist");
        }
        Item item = itemMapper.fromCreateItemDTO(itemDTO);
        item.setCategory(categoryRepository.returnCategory(itemDTO.category()).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"category")
                )
        ));
        item.setItemId(UUID.randomUUID());
        ResponseItemDTO responseDTO =itemMapper.fromItemToResponseItemDTO(itemRepository.save(item));
        responseDTO.setItemId(item.getItemId());
        return responseDTO;
    }

    public ResponseItemDTO getItemByName(String itemName){
        Item item = itemRepository.returnItem(itemName).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"item")
                )
        );
        return itemMapper.fromItemToResponseItemDTO(item);

    }

    public ResponseItemDTO getItemById(String itemId){
        Item item = itemRepository.returnItemById(UUID.fromString(itemId)).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"item")
                )
        );
        return itemMapper.fromItemToResponseItemDTO(item);

    }


    public List<ResponseItemDTO> getAllItems(){
        List<Item> list = itemRepository.findAll();
        return list.stream().map(itemMapper::fromItemToResponseItemDTO).toList();
    }

    public ResponseDTO deleteItem(DeleteItemDTO itemDTO){
        Item item = itemRepository.returnItemById(UUID.fromString(itemDTO.itemId())).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"item")
                )
        );
        itemRepository.delete(item);
        return ResponseDTO.builder()
                .message("Item deleted successfully")
                .build();
    }
}
