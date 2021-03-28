package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.RefreshToken;

import java.util.UUID;

public interface RefreshTokenService {

    Programmer retrieveUser(UUID refreshToken);

    RefreshToken changeRefreshToken(Programmer programmer);

    RefreshToken addRefreshToken(Programmer programmer);

    RefreshToken retrieveRefreshToken(Programmer programmer);

    void removeRefreshToken(UUID refreshToken);

}
