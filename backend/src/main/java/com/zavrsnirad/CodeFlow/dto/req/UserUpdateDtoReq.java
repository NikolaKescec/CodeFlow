package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UserUpdateDtoReq {

    @NotEmpty(message = "Username can not be empty!")
    private String username;

    @NotEmpty
    @Min(value = 8, message = "Password must be minimum 8 characters long")
    private String password;

    private String newPassword;

    public UserUpdateDtoReq(@NotEmpty(message = "Username can not be empty!") String username, @NotEmpty @Min(value = 8, message = "Password must be minimum 8 characters long") String password, String newPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
