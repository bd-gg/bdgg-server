package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.Group;
import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.MatchListDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import gg.boardgame.bdgg.dto.GroupDTO;
import gg.boardgame.bdgg.dto.GroupListDTO;

public interface GroupService {
    MatchListDTO getMatchList(long id, Pageable pageable);
    MatchDTO.Request createMatch(MatchDTO.Request match, Long groupId);
    GroupDTO createGroup(GroupDTO group, long leaderId);
    GroupListDTO getGroupList();
    GroupDTO updateGroup(GroupDTO group);
    GroupDTO createGroupDtoFromGroup(Group group);
}
