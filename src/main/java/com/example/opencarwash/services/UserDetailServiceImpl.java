package com.example.opencarwash.services;

import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.UserRepository;
import com.example.opencarwash.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = repo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new UsernameNotFoundException("Incorrect phone provided.")
        );

        return new UserDetailsImpl(user);
    }
}
