package com.example.jwt_demo.service;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findByName(String name);
}
