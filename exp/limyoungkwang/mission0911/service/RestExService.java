package com.example.restapi_ex.service;

import com.example.restapi_ex.dto.ItemDto;
import com.example.restapi_ex.entity.Item;
import com.example.restapi_ex.mapper.ExMapper;
import com.example.restapi_ex.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class RestExService {

    private final ExMapper exMapper;
    private final ItemRepository itemRepository;

    public RestExService(ExMapper exMapper, ItemRepository itemRepository) {
        this.exMapper = exMapper;
        this.itemRepository = itemRepository;
    }

//    @Autowired
//    private SessionFactory sessionFactory;

    public boolean registerItem(ItemDto itemDto) {
        //DB insert
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("name", itemDto.getName());
//        paramMap.put("id", itemDto.getId());
//
//        exMapper.registerItem(paramMap);
//        exMapper.registerItem(itemDto);
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();

        Item item = new Item();
        item.setName(itemDto.getName());
        item.setId(itemDto.getId());

//        session.save(item);
//        session.getTransaction().commit();
//        session.close();

        itemRepository.save(item);

        log.info("service : register...");
        return true;
    }

    public ItemDto getItem(String id) {
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("id", id);
//
//        HashMap<String, Object> res = exMapper.findById(paramMap);

//        ItemDto itemDto = new ItemDto();
//        itemDto.setId((String)res.get("ID"));
//        itemDto.setName((String)res.get("NAME"));

        Item item = itemRepository.findById(id).get();

//        Session session = sessionFactory.openSession();
//        Item item = session.get(Item.class, id);
//        session.close();

        if(item == null) {
            return null;
        }

        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());

        return itemDto;
    }
}
