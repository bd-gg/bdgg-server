package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.Group;
import gg.boardgame.bdgg.dto.MatchListDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

import gg.boardgame.bdgg.dto.GroupDTO;
import gg.boardgame.bdgg.dto.GroupListDTO;

public interface GroupService {
    MatchListDTO getMatchList(long id, Pageable pageable) throws ResourceNotFoundException;
    GroupDTO createGroup(GroupDTO group, long leaderId);
    GroupListDTO getGroupList();
    GroupDTO updateGroup(GroupDTO group);
    GroupDTO createGroupDtoFromGroup(Group group);
}
