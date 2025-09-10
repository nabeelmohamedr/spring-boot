package com.optimus.omnitrix.securityconfig;

import com.optimus.omnitrix.jjwt.jwttokens;
import com.optimus.omnitrix.userData.customuserDataservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.swagger.v3.core.util.AnnotationsUtils.getHeader;

@Component
public class jwttokenfilter extends OncePerRequestFilter {
    @Autowired
    private jwttokens  jwttokens;

    @Autowired
    private customuserDataservice customuserDataservice;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
           String bearertoken= request.getHeader("Authorization");

           if(bearertoken== null || !bearertoken.startsWith("Bearer "))
           {
               filterChain.doFilter( request,  response); //send to securityconfic  security filter chain
               return;
           }
           String token= bearertoken.substring(7);

           try{
                String username= jwttokens.extractUsername(token);
                if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null)
                {
                    UserDetails userDetails=customuserDataservice.loadUserByUsername(username);
                  if(jwttokens.validateToken(token,userDetails))
                  {
                      UsernamePasswordAuthenticationToken authToken =
                              new UsernamePasswordAuthenticationToken(  //if atches give on token using uname and pass for the u and p
                                      userDetails.getUsername(),
                                      userDetails.getPassword(),
                                      userDetails.getAuthorities()
                              );
                      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //storing the authentication detail from the requst
                                                                                                        //to be stored in the up auth obj
                      SecurityContextHolder.getContext().setAuthentication(authToken);



                  }
                }
           } catch (Exception e) {
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              return;
//           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)       //exception if no user or password fails
//                       .body(Map.of("error", "Invalid username or password"));
           }
        filterChain.doFilter( request,  response);
    }
}
