package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.dto.json.UserDtoJson;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperList;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperUser;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;
import com.zavrsnirad.CodeFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserDtoReq newUser) {
        if(newUser == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user data.");
        userService.addUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Please login with your new username and password.");
    }
    @GetMapping("/top/tasks")
    public ResponseEntity<?> topTaskers() {
        return ResponseEntity.status(HttpStatus.OK).body(MapperList.getList(userService.getUsersTaskers(), MapperUser::UserToJson));
    }

    @GetMapping("/top/solutions")
    public ResponseEntity<?> topSolvers() {
        return ResponseEntity.status(HttpStatus.OK).body(MapperList.getList(userService.getUsersSolvers(), MapperUser::UserToJson));
    }

}
