package com.example.restapi_ex.repository;

import com.example.restapi_ex.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
