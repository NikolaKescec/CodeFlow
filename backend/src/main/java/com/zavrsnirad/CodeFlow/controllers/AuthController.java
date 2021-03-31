package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.RefreshToken;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperUser;
import com.zavrsnirad.CodeFlow.dto.req.AuthReq;
import com.zavrsnirad.CodeFlow.service.RefreshTokenService;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import com.zavrsnirad.CodeFlow.util.CookieUtils;
import com.zavrsnirad.CodeFlow.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody AuthReq authRequest, HttpServletResponse response) throws NoSuchAlgorithmException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Incorrect username or password", e);
        }

        final Programmer programmer = programmerService.findByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(programmer);

        RefreshToken newRefreshToken;

        // TODO: check and add token
        RefreshToken oldRefreshToken = refreshTokenService.retrieveRefreshToken(programmer);
        if(oldRefreshToken == null)
            newRefreshToken = refreshTokenService.addRefreshToken(programmer);
        else
            newRefreshToken = refreshTokenService.changeRefreshToken(programmer);

        setCookies(newRefreshToken.getRefreshToken(), jwt, response, 120, 3600*24*1000);

        return ResponseEntity.ok(MapperUser.UserToJson(programmer));
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public ResponseEntity<?> chechAuthToken() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAuthToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            // check users refresh token and retrieve his information
            Cookie[] cookies = request.getCookies();
            if(cookies == null)
                throw new IllegalArgumentException();

            UUID refreshToken = CookieUtils.extractRefreshTokenFromCookies(cookies);

            if(refreshToken == null)
                throw new IllegalArgumentException();

            Programmer programmer = refreshTokenService.retrieveUser(refreshToken);

            // create new refresh token and set it in the database
            RefreshToken newRefreshToken = refreshTokenService.changeRefreshToken(programmer);

            // save the refresh token in a new cookie and make a new jwt
            String newJwt = jwtTokenUtil.generateToken(programmer);

            setCookies(newRefreshToken.getRefreshToken(), newJwt, response, 120, 3600*24*1000);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | NoSuchAlgorithmException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("\"message\": \"" +"Invalid refresh token." + "\"");
        }
    }

    @RequestMapping(value = "/deauthenticate", method = RequestMethod.GET)
    public ResponseEntity<?> logout(HttpServletResponse response){
        setCookies(null, null, response, 0, 0);
        return ResponseEntity.ok().build();
    }

    private Cookie setRefreshCookie(UUID refreshToken, int expiration) {
        Cookie cookie = new Cookie("Refresh-token", refreshToken == null ? "" : refreshToken.toString());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiration);
        cookie.setPath("/refresh");
        return cookie;
    }

    private Cookie setJwtCookie(String jwt, int expiration) {
        Cookie cookie = new Cookie("Access-token", jwt == null ? "" : jwt);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiration);
        return cookie;
    }

    private void setCookies(UUID refreshToken, String jwt, HttpServletResponse response, int jwtExpiration, int refreshExpiration) {
        Cookie refreshTokenCookie = setRefreshCookie(refreshToken, refreshExpiration);
        Cookie jwtCookie = setJwtCookie(jwt, jwtExpiration);
        response.addCookie(refreshTokenCookie);
        response.addCookie(jwtCookie);
    }

}
