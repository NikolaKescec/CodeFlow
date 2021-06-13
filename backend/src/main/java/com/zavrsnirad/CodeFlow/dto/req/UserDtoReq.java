package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UserDtoReq {

    @NotEmpty(message = "Username can not be empty!")
    private String username;

    @NotEmpty(message = "E-mail can not be empty!")
    private String email;

    @NotEmpty
    @Min(value = 8, message = "Password must be minimum 8 characters long")
    private String password;

    public UserDtoReq() {
    }

    public UserDtoReq(@NotEmpty String username, @NotEmpty String email, @NotEmpty @Min(value = 8, message = "Password must be minimum 8 characters long") String password) {
        this.username = username;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
