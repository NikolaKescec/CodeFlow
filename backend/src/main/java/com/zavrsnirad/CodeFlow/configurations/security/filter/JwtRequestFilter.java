package com.zavrsnirad.CodeFlow.configurations.security.filter;

import com.zavrsnirad.CodeFlow.configurations.security.MyUserDetailsService;
import com.zavrsnirad.CodeFlow.configurations.security.authpoint.JwtAuthenticationPoint;
import com.zavrsnirad.CodeFlow.util.CookieUtils;
import com.zavrsnirad.CodeFlow.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtAuthenticationPoint jwtAuthenticationPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        Cookie[] cookies = httpServletRequest.getCookies();
        String jwt = null;
        String username = null;

        // ignored urls for jwt-s
        if(!(httpServletRequest.getRequestURI().equals("/authenticate")
                || httpServletRequest.getRequestURI().equals("/refresh")
                || httpServletRequest.getRequestURI().equals("/deauthenticate")
                || httpServletRequest.getRequestURI().equals("/user/register") )) {
            try {
                if(cookies != null) {
                    jwt = CookieUtils.extractFieldFromCookie(cookies, "Access-token");
                    if(jwt != null)
                        username = jwtUtil.extractUsername(jwt);
                }
            } catch (JwtException ex) {
                {
                    jwtAuthenticationPoint.commence(httpServletRequest, httpServletResponse, new BadCredentialsException("Bad authorization."));
                    return;
                }
            }
        }


        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
