package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.Size;

public class AuthReq {

    private String username;

    @Size(min = 8, message = "Password has to be long at least 8 characters!")
    private String password;

    public AuthReq() {
    }

    public AuthReq(String username, @Size(min = 8, message = "Password has to be long at least 8 characters!") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
