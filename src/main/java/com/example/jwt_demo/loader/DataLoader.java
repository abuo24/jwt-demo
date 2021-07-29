package com.example.jwt_demo.loader;// Author - Orifjon Yunusjonov 

import com.example.jwt_demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// t.me/coderr24
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminRepository adminRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    @Override
    public void run(String... args) throws Exception {
        try {
            if (init.equalsIgnoreCase("create")) {
                Role roleUser = new Role();
                roleUser.setId(1L);
                roleUser.setName("ROLE_USER");
                Role roleAdmin = new Role(2L, "ROLE_ADMIN");
                List<Role> roleList = new ArrayList<>(Arrays.asList(roleUser, roleAdmin));
                roleList = roleRepository.saveAll(roleList);
                User user = new User();
                Admin admin = new Admin();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setFullName("user");
                user.setRoles(new ArrayList<>(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("root"));
                admin.setFullName("admin");
                admin.setSocial("t.me/test");
                admin.setRoles(roleList);
                userRepository.save(user);
                adminRepository.save(admin);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

