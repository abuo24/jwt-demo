package com.example.jwt_demo.service;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Admin extends User{
    private String social;
}
