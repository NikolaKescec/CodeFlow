package com.zavrsnirad.CodeFlow.util;

import javax.servlet.http.Cookie;
import java.util.UUID;

public class CookieUtils {


    public static UUID extractRefreshTokenFromCookies (Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("Refresh-token"))
                return UUID.fromString(cookie.getValue());
        }
        return null;
    }

    public static String extractAccessTokenFromCookies (Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("Access-token"))
                return cookie.getValue();
        }
        return null;
    }


}
