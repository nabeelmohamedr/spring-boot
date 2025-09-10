package com.optimus.omnitrix;


import com.optimus.omnitrix.entity.asmith_entity;
import com.optimus.omnitrix.jjwt.jwttokens;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;



@Controller
@ResponseBody
@RequestMapping("ultimate")
public class ultimate {

    @GetMapping("/hello")
    public String home() {
        return "hello_world";
    }

    @Autowired
    private jwttokens jwttokens;

    @Autowired
    private AuthenticationManager authManager; //manage to send token jwt file

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody asmith_entity user) {
        try {
            // authenticate user
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(                    // if the username and pass are same
                            user.getUsername(),                                    //then an obj is created
                            user.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal(); //if the obj generated it give details like principle

            // generate JWT token
            String token = jwttokens.GenerateToken(userDetails);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)       //exception if no user or password fails
                    .body(Map.of("error", "Invalid username or password"));
        }
    }

}
