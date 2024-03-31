package com.dice.weather.service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerUserDetailsService implements UserDetailsService {


   @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       if(userName.equals("Rani"))
       {
           return new User("Rani","rani1234",new ArrayList<>());
       } else {
           throw new UsernameNotFoundException("User Not Found !!");
       }
   }

}
