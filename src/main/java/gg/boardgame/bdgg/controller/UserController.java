package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.dto.*;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import gg.boardgame.bdgg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity getUsers(@RequestParam(value = "username", required = false) String userName) {
        // if length of the userName is not over 2, return error
        if(userName != null && userName.length() < 3)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        UserListDTO users = userService.getUsers(userName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity getProfile(@PathVariable("userName") String userName, Pageable pageable) throws ResourceNotFoundException {
        log.info(String.format("userName: %s", userName));

        ProfileDTO profile = userService.getProfile(userName, pageable);

        // remove password field
        profile.setPassword("");
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("/{user-id}/groups/{group-id}/join")
    public ResponseEntity joinGroup(@PathVariable("user-id") Long userId,
                                    @PathVariable("group-id") Long groupId) {

        GroupDTO group = userService.joinGroup(userId, groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/{user-id}/groups")
    public ResponseEntity getJoinedGroups(@PathVariable("user-id") Long userId) {

        GroupListDTO group = userService.getJoinedGroups(userId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}/groups/{group-id}")
    public ResponseEntity leaveGroup(@PathVariable("user-id") Long userId,
                                     @PathVariable("group-id") Long groupId) {

        GroupDTO group = userService.leaveGroup(userId, groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

}
