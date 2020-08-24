package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.GroupDTO;

public interface UserService {
    public GroupDTO joinGroup(long userId, long groupId);
    public GroupDTO leaveGroup(long userId, long groupId);
}
