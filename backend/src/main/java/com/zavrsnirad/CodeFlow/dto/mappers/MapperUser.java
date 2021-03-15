package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.User;
import com.zavrsnirad.CodeFlow.dto.json.UserDtoJson;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;

public class MapperUser {

    public static UserDtoJson UserToJson(User user) {
        return new UserDtoJson(user.getUserId(), user.getUsername(), user.getPoints());
    }

    public static User UserDtoToUser(UserDtoReq user) {
        return new User(user.getUsername(), user.getEmail(), user.getPassword(), "USER");
    }

}
