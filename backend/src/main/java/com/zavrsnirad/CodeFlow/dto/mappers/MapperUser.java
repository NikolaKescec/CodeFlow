package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.dto.json.UserDtoJson;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;

public class MapperUser {

    public static UserDtoJson UserToJson(Programmer programmer) {
        return new UserDtoJson(
                programmer.getProgrammerId(),
                programmer.getUsername(),
                programmer.getSolutionPoints(),
                programmer.getTaskPoints()
        );
    }

    public static Programmer UserDtoToUser(UserDtoReq user) {
        return new Programmer(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                "USER"
        );
    }

}
