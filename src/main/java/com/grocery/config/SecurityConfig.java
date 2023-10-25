package com.grocery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       
       http
       .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((request) -> request
           .requestMatchers(HttpMethod.GET,"/grocery/v1/allgroceryitems")
           .hasAnyRole("ADMIN", "USER")
           .requestMatchers(HttpMethod.POST,"/grocery/v1/saveGrocery")
           .hasRole("ADMIN")
           .requestMatchers(HttpMethod.DELETE,"/grocery-items/*")
           .hasRole("ADMIN")
          .requestMatchers(HttpMethod.PUT, "/update-grocery")
          .hasRole("ADMIN")
           .anyRequest()
           .authenticated()
        
              )
        .httpBasic(Customizer.withDefaults())
       
    
      
        ;
       
return http.build();

       
       
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
       UserDetails adminuser =
           User.withDefaultPasswordEncoder()
             .username("admin")
             .password("secret")
             .roles("ADMIN")
             .build();
       UserDetails coreuser =
             User.withDefaultPasswordEncoder()
             .username("user")
             .password("secret")
             .roles("USER")
             .build();

       return new InMemoryUserDetailsManager(adminuser, coreuser);
    }
    
       
       
    
    

}

