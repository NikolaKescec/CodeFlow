package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.dto.json.UserDtoJson;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperUser;
import com.zavrsnirad.CodeFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        UserDtoJson user = MapperUser.UserToJson(userService.findByUsername(username));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers(){
        List<UserDtoJson> users = userService.getUsers().stream().map(MapperUser::UserToJson).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}
