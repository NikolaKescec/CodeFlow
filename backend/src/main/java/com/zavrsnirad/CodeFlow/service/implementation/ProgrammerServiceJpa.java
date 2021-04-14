package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;
import com.zavrsnirad.CodeFlow.repository.ProgrammerRepository;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammerServiceJpa implements ProgrammerService {

    @Autowired
    private ProgrammerRepository programmerRepository;

    @Override
    public Programmer findByUsername(String username) {
        Programmer programmer = programmerRepository.findByUsername(username);
        if(programmer == null)
            throw new IllegalArgumentException("This username does not exist!");
        return programmer;
    }

    @Override
    public List<Programmer> getProgrammersTaskers() {
        return programmerRepository.findTop20ByOrderByTaskPointsDesc();
    }

    @Override
    public List<Programmer> getProgrammersSolvers() {
        return programmerRepository.findTop20ByOrderBySolutionPointsDesc();
    }

    @Override
    public List<Programmer> getUsers() {
        return programmerRepository.findAll();
    }

    @Override
    public Programmer addProgrammer(UserDtoReq user) {
        boolean byUsername, byEmail;

        // check for existing username
        byUsername = programmerRepository.findByUsername(user.getUsername()) != null;
        if(byUsername)
            throw new IllegalArgumentException("Username already taken.");

        // check for existing email
        byEmail = programmerRepository.findByEmail(user.getEmail()) != null;
        if(byEmail)
            throw new IllegalArgumentException("Email already taken.");

        // User cretion
        Programmer newProgrammer = new Programmer(user.getUsername(), user.getEmail(), "USER");

        // Ekripcija lozinke.
        String salt = BCrypt.gensalt(12);
        String hashed = BCrypt.hashpw(user.getPassword(), salt);
        newProgrammer.setPassword(hashed);
        newProgrammer.setUserCreated(newProgrammer.getUsername());

        // saving user
        newProgrammer = programmerRepository.save(newProgrammer);
        return newProgrammer;
    }

    @Override
    public void removeProgrammer(String username) {
        Programmer programmer = programmerRepository.findByUsername(username);
        if(programmer == null)
            throw new IllegalArgumentException("This username does not exist!");
        programmerRepository.delete(programmer);
    }

    // TODO
    @Override
    public Programmer updateProgrammer(Programmer updatedProgrammer) {
        return null;
    }

}
