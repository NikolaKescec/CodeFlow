package com.zavrsnirad.CodeFlow.consolerunner;

import com.zavrsnirad.CodeFlow.domain.User;
import com.zavrsnirad.CodeFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        //bigPP
        userRepository.save(new User("PP", "pp@email.com", "$2y$12$A5dMg1i8IBdguxPHW6y2T.ljLswMdiLoiH0CX3mAVZjHi1Oz9gJBS", "ADMIN"));
        //catCatcat
        userRepository.save(new User("BigBertha", "bigBertha@email.com", "$2y$12$6J74jRs1b6LVcLGvWVm37OsCGQpAUM7UDkoEzbnEneVm8kqLgV.ke","USER"));
        //konzumWOM
        userRepository.save(new User("Lelok", "Lelok@email.com", "$2y$12$TM1DnU.F2iYaACgfQWFsruC2zcSxKM7xT6Mh/stbxWvlWz0roTffy", "USER"));
        //motoriImotori
        userRepository.save(new User("Kovakov", "Kovakov@email.com", "$2y$12$oW4jFJOCFc07n4lmWbWSuOwAs0tnIKe4z4PZ1vtEeHojAFVy9oT9O",  "USER"));
        //lošMikrofonGuy
        userRepository.save(new User("Mucero", "Mucero@email.com", "$2y$12$W0EmyNEPJ7PyPubegYF/sO3uEp4EnhXBNMZmFVHI2U4a6ff1V2K36",  "USER"));
    }

}
