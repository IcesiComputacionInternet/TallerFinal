package com.icesi.backend.service;

import com.icesi.backend.DTO.ItemCreateDTO;
import com.icesi.backend.mappers.ItemMapper;
import com.icesi.backend.models.Item;
import com.icesi.backend.respositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;

import javax.swing.text.html.Option;
import java.util.Optional;

public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public Optional<Item> createItem(ItemCreateDTO itemCreateDTO){
        return Optional.of(itemRepository.save(itemMapper.fromItemCreateDTO(itemCreateDTO)));
    }
}
