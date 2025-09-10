package com.optimus.omnitrix.securityconfig;


import com.optimus.omnitrix.userData.customuserDataservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration // Marks this class as a Spring configuration class
@EnableWebSecurity //to alter the security config,Activates Spring Security for this project.
public class securityconfig {

    @Autowired
    private jwttokenfilter jwttokenfilter;

    @Bean
    public SecurityFilterChain SecureFilterChain(HttpSecurity http) throws Exception { //HttpSecurity as a parameter, which Spring injects automatically.
        http.authorizeHttpRequests(authz -> //authz ia a parameter come from spring core
                authz .requestMatchers(HttpMethod.POST,"/omn/triger").permitAll()
                      //  .requestMatchers(HttpMethod.GET,"/omn/trigerp").permitAll()
                        .requestMatchers("/omn/**").authenticated()
                        .requestMatchers("/home/**").permitAll()
                        .anyRequest().permitAll()
        )//.formLogin(form -> form.permitAll().defaultSuccessUrl("/hello"))
                .formLogin(form -> {
                    SavedRequestAwareAuthenticationSuccessHandler successHandler =
                            new SavedRequestAwareAuthenticationSuccessHandler();
                    successHandler.setAlwaysUseDefaultTargetUrl(false); //  redirect back to original URL
                    form.successHandler(successHandler)         //buu session is in off
                            .permitAll(); // allow all users to access the login form itself
                })//tomake visible of login default  page
                .csrf( csrf -> csrf.disable())
                .sessionManagement(ses-> ses.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) )

                .addFilterBefore(jwttokenfilter, UsernamePasswordAuthenticationFilter.class); //1st before 2nd


        return http.build();

    }
//this is for inmemmory  data management
//    @Bean
//    public UserDetailsService userDetails(PasswordEncoder pass){
//        UserDetails user= User.withUsername("shae").password(pass.encode("123456")).roles("USER").build();
//
//        UserDetails admin= User.withUsername("rosh")
//                            .password(pass.encode("54321")) //That pass is actually your BCryptPasswordEncoder bean (Spring injected it).
//                                .roles("ADMIN").
//                                    build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }
    @Bean
    public UserDetailsService userDetails(){

        return new customuserDataservice();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetails());
        authProvider.setPasswordEncoder(passwordEncod());  // Works now
        return authProvider;
    }



    @Bean
    public PasswordEncoder passwordEncod(){
        return  new BCryptPasswordEncoder(); // “Spring Security, whenever you need to handle passwords, use BCrypt.”
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(List.of(authenticationProvider())); //we have only one dao auth provider so we give it
    }

}
