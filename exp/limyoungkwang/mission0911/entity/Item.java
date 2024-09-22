package com.example.restapi_ex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

    @Id
    private String id;
    private String name;
}
