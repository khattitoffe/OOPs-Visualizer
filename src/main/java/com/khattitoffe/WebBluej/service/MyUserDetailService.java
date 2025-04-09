package com.khattitoffe.WebBluej.service;

import com.khattitoffe.WebBluej.entity.UserData;
import com.khattitoffe.WebBluej.repository.CreateUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    CreateUserRepo createUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       UserData user;

       try {
          user = createUserRepo.findByusername(username);
       }
       catch(Exception e)
       {
           throw new UsernameNotFoundException("Username not found (JWT)");
       }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );

    }
}
