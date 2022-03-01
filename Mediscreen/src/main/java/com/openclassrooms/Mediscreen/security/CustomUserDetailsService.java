package com.openclassrooms.Mediscreen.security;

import com.openclassrooms.Mediscreen.entity.Login;
import com.openclassrooms.Mediscreen.entity.Patient;
import com.openclassrooms.Mediscreen.entity.Praticien;
import com.openclassrooms.Mediscreen.microservice.SqlApiMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private Patient patient;
    private Praticien praticien;
    @Autowired
    private SqlApiMicroservice sqlApiMicroservice;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String userEmail = "";
        String userPassword = "";
        boolean isPraticient = false;
        if (email.trim().isEmpty()) {
            throw new UsernameNotFoundException("username is empty");
        }

        Login login = sqlApiMicroservice.login(email);

        if (login.getPatient().isPresent()) {
            userEmail = login.getPatient().get().getEmail();
            userPassword = login.getPatient().get().getPassword();
        }else if (login.getPraticien().isPresent()) {
            userEmail = login.getPraticien().get().getEmail();
            userPassword = login.getPraticien().get().getPassword();
            isPraticient = true;
        }else {
            throw new UsernameNotFoundException("User " + email + " not found");
        }
        return new org.springframework.security.core.userdetails.User(userEmail, userPassword, getGrantedAuthorities());
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

}
