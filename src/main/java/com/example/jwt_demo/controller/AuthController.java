package com.example.jwt_demo.controller;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.jwt_demo.loader.Vm;
import com.example.jwt_demo.security.JwtTokenProvider;
//import com.example.jwt_demo.security.SecurityUtils;
import com.example.jwt_demo.service.User;
import com.example.jwt_demo.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Vm vm){
        String username = vm.getUsername();
        String password = vm.getPassword();
//        try {

//        System.out.println(userRepository.findAll().get(1).getUsername());

        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("dsa"));
        System.out.println(user.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map map = new HashMap();
            map.put("token", token);
            map.put("username",username);
            map.put("success", true);
            return ResponseEntity.ok(map);
//        } catch (Exception e){
//            return new ResponseEntity("error", HttpStatus.BAD_REQUEST);
//        }
    }

    @GetMapping("/test")
    public String test(){
//        Optional<User> cUser = userRepository.findByUsername(SecurityUtils.getCurrentUsername().get());
//        System.out.println(cUser.get().getUsername()+"  "+cUser.get().getPassword());

        return "test";
    }

}
