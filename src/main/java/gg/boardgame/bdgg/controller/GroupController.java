package gg.boardgame.bdgg.controller;

import com.google.gson.JsonObject;
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
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(path = "/{groupId}/matches", method = RequestMethod.GET)
    public ResponseEntity<List<Map.Entry<String,Long>>> getMatchIds(@PathVariable("groupId") long groupId,
                                      Pageable pageable) throws ResourceNotFoundException {
        log.info("getMatchIds is called");
        log.info(String.format("groupId: %d", groupId));

        // call service
        List<Map.Entry<String,Long>> matchIds = groupService.getMatchIds(groupId, pageable);

        return ResponseEntity.ok(matchIds);
    }
}

