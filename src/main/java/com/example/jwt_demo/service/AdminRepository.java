package com.example.jwt_demo.service;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
