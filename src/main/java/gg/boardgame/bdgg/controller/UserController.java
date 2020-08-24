package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.dto.GroupDTO;
import gg.boardgame.bdgg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{user-id}/groups/{group-id}")
    public ResponseEntity joinGroup(@PathVariable("user-id") Long userId,
                                    @PathVariable("group-id") Long groupId) {

        GroupDTO group = userService.joinGroup(userId, groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}/groups/{group-id}")
    public ResponseEntity leaveGroup(@PathVariable("user-id") Long userId,
                                     @PathVariable("group-id") Long groupId) {

        GroupDTO group = userService.leaveGroup(userId, groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

}
