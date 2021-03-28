package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.RefreshToken;
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
    public Programmer retrieveUser(UUID refreshToken) {
        RefreshToken foundRefreshToken = findRefreshToken(refreshToken);

        return foundRefreshToken.getUser();
    }

    @Override
    public RefreshToken changeRefreshToken(Programmer programmer) {
        RefreshToken oldRefreshToken = refreshTokenRepository.findByProgrammerId(programmer.getProgrammerId());
        if(oldRefreshToken == null)
            throw new IllegalArgumentException("No such entry!");

        UUID newRefreshToken = UUID.randomUUID();
        oldRefreshToken.setRefreshToken(newRefreshToken);
        refreshTokenRepository.save(oldRefreshToken);

        return oldRefreshToken;
    }

    @Override
    public RefreshToken addRefreshToken(Programmer programmer) {
        UUID token = UUID.randomUUID();
        RefreshToken refreshToken = new RefreshToken(token, programmer);
        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    @Override
    public RefreshToken retrieveRefreshToken(Programmer programmer) {
        return refreshTokenRepository.findByProgrammerId(programmer.getProgrammerId());
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
