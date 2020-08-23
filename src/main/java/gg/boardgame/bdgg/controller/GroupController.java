package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import gg.boardgame.bdgg.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(path = "/groups/{groupId}/matches", method = RequestMethod.GET)
    public ResponseEntity<List<Long>> getMatchIds(@PathVariable("groupId") long groupId,
                                      Pageable pageable) throws ResourceNotFoundException {
        log.info("getMatchIds is called");
        log.info(String.format("groupId: %l", groupId));

        // call service
        List<Long> matchIds = groupService.getMatchIds(groupId, pageable);

        return ResponseEntity.ok(matchIds);
    }
}

