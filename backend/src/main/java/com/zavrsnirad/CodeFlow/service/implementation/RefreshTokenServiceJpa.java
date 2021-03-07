package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.RefreshToken;
import com.zavrsnirad.CodeFlow.domain.User;
import com.zavrsnirad.CodeFlow.repository.RefreshTokenRepository;
import com.zavrsnirad.CodeFlow.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RefreshTokenServiceJpa implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public User retrieveUser(UUID refreshToken) {
        RefreshToken foundRefreshToken = findRefreshToken(refreshToken);

        return foundRefreshToken.getUser();
    }

    @Override
    public RefreshToken changeRefreshToken(User user) {
        RefreshToken oldRefreshToken = refreshTokenRepository.findByUserId(user.getUserId());
        if(oldRefreshToken == null)
            throw new IllegalArgumentException("No such entry!");

        UUID newRefreshToken = UUID.randomUUID();
        oldRefreshToken.setRefreshToken(newRefreshToken);
        refreshTokenRepository.save(oldRefreshToken);

        return oldRefreshToken;
    }

    @Override
    public RefreshToken addRefreshToken(User user) {
        UUID token = UUID.randomUUID();
        RefreshToken refreshToken = new RefreshToken(token, user);
        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    @Override
    public RefreshToken retrieveRefreshToken(User user) {
        return refreshTokenRepository.findByUserId(user.getUserId());
    }

    @Override
    public void removeRefreshToken(UUID refreshToken) {
        RefreshToken foundRefreshToken = findRefreshToken(refreshToken);
        refreshTokenRepository.delete(foundRefreshToken);
    }

    private RefreshToken findRefreshToken(UUID refreshToken) {
        RefreshToken foundRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
        if(foundRefreshToken == null)
            throw new IllegalArgumentException("Invalid token!");
        return foundRefreshToken;
    }

}
