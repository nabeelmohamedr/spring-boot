package com.optimus.omnitrix.userData;

import com.optimus.omnitrix.entity.asmith_entity;
import com.optimus.omnitrix.repo.asmith_repositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class customuserDataservice implements UserDetailsService {

    @Autowired
    private asmith_repositary asmith_repositary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        asmith_entity user=asmith_repositary.findByusername(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        return new User(user.getUsername(),user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("user..role")));//you are hardcoding every user to have exactly one authority: "user..role" (literally that string).
    }
}
