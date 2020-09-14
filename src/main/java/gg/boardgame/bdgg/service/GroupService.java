package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.Group;
import gg.boardgame.bdgg.dto.*;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    MatchListDTO getMatchList(long id, Pageable pageable);
    MatchDTO.Request createMatch(MatchDTO.Request match, Long groupId);
    GroupDTO createGroup(GroupDTO group, long leaderId);
    GroupListDTO getGroupList();
    GroupDTO updateGroup(GroupDTO group);
    GroupDTO createGroupDtoFromGroup(Group group);
    UserListDTO getGroupMemberList(long id);
}
