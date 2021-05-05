package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.Follower;
import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.dto.json.FollowerDtoJson;
import com.zavrsnirad.CodeFlow.dto.json.UserDtoJson;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperFollower;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperList;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperUser;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.UserUpdateDtoReq;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import com.zavrsnirad.CodeFlow.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/programmer")
public class UserController {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private ProgrammerService programmerService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        UserDtoJson user = MapperUser.UserToJson(programmerService.findByUsername(username));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers(){
        List<UserDtoJson> users = programmerService.getUsers().stream().map(MapperUser::UserToJson).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserDtoReq newUser) {
        if(newUser == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user data.");
        programmerService.addProgrammer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Please login with your new username and password.");
    }
    @GetMapping("/top/taskers")
    public ResponseEntity<?> topTaskers() {
        return ResponseEntity.status(HttpStatus.OK).body(MapperList.getList(programmerService.getProgrammersTaskers(), MapperUser::UserToJson));
    }

    @GetMapping("/top/solvers")
    public ResponseEntity<?> topSolvers() {
        return ResponseEntity.status(HttpStatus.OK).body(MapperList.getList(programmerService.getProgrammersSolvers(), MapperUser::UserToJson));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDtoReq userUpdateDtoReq, Principal principal, HttpServletResponse response) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        programmerService.updateProgrammer(userUpdateDtoReq, programmer);
        try {
            // create new refresh token
            String newJwt = jwtTokenUtil.generateToken(programmer, 120);
            // save the refresh token in a new cookie and make a new jwt
            String newRefreshToken = jwtTokenUtil.generateToken(programmer, 3600*24);
            AuthController.setCookies(newRefreshToken, newJwt, response, 120, 3600*24);
        } catch ( NoSuchAlgorithmException ex) {
            throw new IllegalArgumentException("Invalid jwt token regeneration!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(MapperUser.UserToJson(programmer));
    }

    @GetMapping("/follow/{userId}")
    public ResponseEntity<?> followUser(@PathVariable("userId") Long toFollowId, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        Follower followership = programmerService.followUser(toFollowId, programmer);
        return ResponseEntity.status(HttpStatus.OK).body(MapperFollower.FollowerToJson(followership));
    }

    @GetMapping("/accept-followership/{notificationId}/{followerProgrammer}")
    public ResponseEntity<?> acceptFollowership(@PathVariable("notificationId") Long notificationId, @PathVariable("followerProgrammer") String followerProgrammer, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        Programmer follower = programmerService.findByUsername(followerProgrammer);
        programmerService.acceptFollowerShip(notificationId, follower.getProgrammerId(), programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/deny-followership/{notificationId}/{followerProgrammer}")
    public ResponseEntity<?> denyFollowership(@PathVariable("notificationId") Long notificationId, @PathVariable("followerProgrammer") String followerProgrammer, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        Programmer follower = programmerService.findByUsername(followerProgrammer);
        programmerService.denyFollower(notificationId, follower.getProgrammerId(), programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/unfollow/{followerShipId}")
    public ResponseEntity<?> unfollowUser(@PathVariable("followerShipId") Long followershipToUnfollowId, Principal principal) {
        programmerService.unfollowUser(followershipToUnfollowId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/following/{programmerName}")
    public ResponseEntity<?> getFollowedByName(@PathVariable("programmerName") String name, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        Programmer inspectedProgrammer = programmerService.findByUsername(name);
        Follower follower = programmerService.followingUser(inspectedProgrammer.getProgrammerId(), programmer);
        return ResponseEntity.ok(follower == null ? new FollowerDtoJson(null, false, false) : MapperFollower.FollowerToJson(follower));
    }

}
