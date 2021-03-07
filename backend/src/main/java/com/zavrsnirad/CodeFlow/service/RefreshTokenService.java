package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.RefreshToken;
import com.zavrsnirad.CodeFlow.domain.User;

import java.util.UUID;

public interface RefreshTokenService {

    User retrieveUser(UUID refreshToken);

    RefreshToken changeRefreshToken(User user);

    RefreshToken addRefreshToken(User user);

    RefreshToken retrieveRefreshToken(User user);

    void removeRefreshToken(UUID refreshToken);

}
