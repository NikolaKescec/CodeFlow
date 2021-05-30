package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.RefreshToken;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperUser;
import com.zavrsnirad.CodeFlow.dto.req.AuthReq;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import com.zavrsnirad.CodeFlow.util.CookieUtils;
import com.zavrsnirad.CodeFlow.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProgrammerService programmerService;

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
        final String jwt = jwtTokenUtil.generateToken(programmer, 120);

        RefreshToken newRefreshToken;

        // TODO: check and add token
        /*RefreshToken oldRefreshToken = refreshTokenService.retrieveRefreshToken(programmer);
        if(oldRefreshToken == null)
            newRefreshToken = refreshTokenService.addRefreshToken(programmer);
        else
            newRefreshToken = refreshTokenService.changeRefreshToken(programmer);*/
        final String refreshToken = jwtTokenUtil.generateToken(programmer, 3600*24*1000);

        setCookies(refreshToken, jwt, response, 120, 3600*24*1000);

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

            String refreshToken = CookieUtils.extractFieldFromCookie(cookies, "Refresh-token");

            if(refreshToken == null)
                throw new IllegalArgumentException();

            String username = jwtTokenUtil.extractUsername(refreshToken);
            Programmer programmer = programmerService.findByUsername(username);

            // create new refresh token
            String newJwt = jwtTokenUtil.generateToken(programmer, 120);
            // save the refresh token in a new cookie and make a new jwt
            String newRefreshToken = jwtTokenUtil.generateToken(programmer, 3600*24);

            setCookies(newRefreshToken, newJwt, response, 120, 3600*24);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | NoSuchAlgorithmException | JwtException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("\"message\": \"" +"Invalid refresh token." + "\"");
        }
    }

    @RequestMapping(value = "/deauthenticate", method = RequestMethod.GET)
    public ResponseEntity<?> logout(HttpServletResponse response){
        setCookies(null, null, response, 0, 0);
        return ResponseEntity.ok().build();
    }

    protected static ResponseCookie setRefreshCookie(String refreshToken, int expiration) {
        return ResponseCookie.from("Refresh-token", refreshToken == null ? "" : refreshToken)
                .secure(true)
                .httpOnly(true)
                .path("/refresh")
                .maxAge(expiration)
                .sameSite("None")
                .build();
    }

    protected static ResponseCookie setJwtCookie(String jwt, int expiration) {
        return ResponseCookie.from("Access-token", jwt == null ? "" : jwt)
                .secure(true)
                .httpOnly(true)
                .path("/")
                .maxAge(expiration)
                .sameSite("None")
                .build();
    }

    protected static void setCookies(String refreshToken, String jwt, HttpServletResponse response, int jwtExpiration, int refreshExpiration) {
        ResponseCookie refreshTokenCookie = setRefreshCookie(refreshToken, refreshExpiration);
        ResponseCookie jwtCookie = setJwtCookie(jwt, jwtExpiration);
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
    }

}
