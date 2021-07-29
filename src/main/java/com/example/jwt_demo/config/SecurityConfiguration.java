package com.example.jwt_demo.config;// Author - Orifjon Yunusjonov 
// t.me/coderr24

//import com.example.jwt_demo.security.JwtConfigurer;
import com.example.jwt_demo.security.JwtConfigurer;
import com.example.jwt_demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

//    private final UserDetailsService userDetailsService;
//
//    public SecurityConfiguration(@Lazy UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Bean
//    BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .exceptionHandling()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/auth/test").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().disable()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
