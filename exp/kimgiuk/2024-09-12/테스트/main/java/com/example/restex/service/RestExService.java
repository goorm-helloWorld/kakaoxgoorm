package com.example.restex.service;

import com.example.restex.dto.ItemDto;
import com.example.restex.entity.ItemEntity;
import com.example.restex.mapper.ExMapper;
import com.example.restex.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class RestExService {
//    @Autowired
//    private ExMapper exMapper;
//
    @Autowired
    private ItemRepository itemRepository;

//    @Autowired
//    private SessionFactory sessionFactory;

    public boolean registerItem(ItemDto itemDto) {
        // DB insert
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("id", itemDto.getId());
//        paramMap.put("name", itemDto.getName());
//
//        exMapper.registerItem(paramMap);
//        exMapper.registerItem(itemDto);

        // Hibernate Session 사용 데이터 저장
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemDto.getId());
        itemEntity.setName(itemDto.getName());
//
        itemRepository.save(itemEntity);
//
//        session.save(itemEntity);
//        session.getTransaction().commit();
//        session.close();

        log.info("service: register...");

        //DB insert가 성공했을 경우 true
        return true;
    }

    public ItemDto getItem(String id) {
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("id", id);
//
//        HashMap<String, Object> res = exMapper.findById(paramMap);

//        //ItemDto 타입 리턴
//        ItemDto itemDto = new ItemDto();
//        itemDto.setId((String)res.get("ID"));
//        itemDto.setName((String)res.get("NAME"));

        ItemEntity itemEntity = itemRepository.findById(id).get();

        // Hibernate 세션을 사용하여 데이터를 조회
//        Session session = sessionFactory.openSession();
//        ItemEntity itemEntity = session.get(ItemEntity.class, id);
//        session.close();
//
//        if (itemEntity == null) {
//            return null;
//        }

        //ItemDto 타입 리턴
        ItemDto itemDto = new ItemDto();
        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());

        return itemDto;
    }
}
