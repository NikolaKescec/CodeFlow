package com.zavrsnirad.CodeFlow.configurations.security;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.repository.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ProgrammerRepository programmerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final Programmer programmer = programmerRepository.findByUsername(s);
        if(programmer == null) {
            throw new UsernameNotFoundException(s);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(programmer.getUsername())
                .password(programmer.getPassword())
                .authorities(programmer.getRole())
                .build();
    }
}
