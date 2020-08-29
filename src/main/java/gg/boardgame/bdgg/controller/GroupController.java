package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.dto.GroupDTO;
import gg.boardgame.bdgg.dto.GroupListDTO;
import gg.boardgame.bdgg.dto.MatchListDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import gg.boardgame.bdgg.service.GroupService;
import gg.boardgame.bdgg.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{groupId}/matches")
    public ResponseEntity getMatchlist(@PathVariable("groupId") long groupId,
                                      Pageable pageable) throws ResourceNotFoundException {
        log.info("getMatchList is called");
        log.info(String.format("groupId: %d", groupId));

        // call service
        MatchListDTO resMatchList = groupService.getMatchList(groupId, pageable);

        return new ResponseEntity<>(resMatchList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createGroup(@RequestBody GroupDTO group) {
        // get leader's id from token
        long id = 0;

        GroupDTO resGroup = null;
        try {
            resGroup = groupService.createGroup(group, id);
        } catch(Exception e) { // should be specified. exception e is to rough
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // error message type will be included
        }

        return new ResponseEntity<>(resGroup, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getGroupList() {
        // verify token

        GroupListDTO resGroupList = null;
        try {
            resGroupList = groupService.getGroupList();
        } catch (Exception e) { // should be specified. exception e is to rough
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR); // error message type will be included
        }

        return new ResponseEntity<>(resGroupList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getGroupById() {
        // verify token

        return null;
    }

    @PutMapping()
    public ResponseEntity updateGroup(@RequestBody GroupDTO group) {
        if(group.getId() == null) {
            return new ResponseEntity("id must exist", HttpStatus.BAD_REQUEST);
        }

        GroupDTO resGroup = null;

        try {
            resGroup = groupService.updateGroup(group);
        } catch(NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(resGroup, HttpStatus.OK);
    }
}
