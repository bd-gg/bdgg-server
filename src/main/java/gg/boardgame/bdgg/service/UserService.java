package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.GroupDTO;
import gg.boardgame.bdgg.dto.GroupListDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

public interface UserService {
    ProfileDTO getProfile(String userName, Pageable pageable) throws ResourceNotFoundException;
    GroupDTO joinGroup(long userId, long groupId);
    GroupListDTO getJoinedGroups(long userId);
    GroupDTO leaveGroup(long userId, long groupId);
}
