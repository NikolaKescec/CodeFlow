package com.zavrsnirad.CodeFlow.util;

import javax.servlet.http.Cookie;
import java.util.UUID;

public class CookieUtils {


    public static String extractFieldFromCookie (Cookie[] cookies, String field) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(field))
                return cookie.getValue();
        }
        return null;
    }

}
