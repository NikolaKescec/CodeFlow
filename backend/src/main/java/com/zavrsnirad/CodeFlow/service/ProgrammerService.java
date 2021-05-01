package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.UserUpdateDtoReq;

import java.util.List;

public interface ProgrammerService {

    Programmer findByUsername(String username);

    List<Programmer> getProgrammersTaskers();

    List<Programmer> getProgrammersSolvers();

    List<Programmer> getUsers();

    Programmer addProgrammer(UserDtoReq user);

    void removeProgrammer(String username);

    void updateProgrammer(UserUpdateDtoReq userUpdateDtoReq, Programmer programmer);

}
