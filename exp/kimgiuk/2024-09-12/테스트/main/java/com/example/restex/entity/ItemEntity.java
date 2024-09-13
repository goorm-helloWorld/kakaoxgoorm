package com.example.restex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ITEM") // 물리 테이블 이름
@Getter
@Setter
public class ItemEntity {

    @Id
    private String id;
    private String name;
}
