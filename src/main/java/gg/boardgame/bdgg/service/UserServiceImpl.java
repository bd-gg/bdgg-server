package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.dto.GroupDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;


    @Override
    public GroupDTO joinGroup(long userId, long groupId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Group foundGroup = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException());

        // create GroupMember entity
        GroupMember gm = new GroupMember();
        gm.setUser(foundUser);
        gm.setGroup(foundGroup);
        groupMemberRepository.save(gm);

        // create return groupDTO
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.copyFromGroupDO(foundGroup);

        return groupDTO;
    }

    @Override
    public GroupDTO leaveGroup(long userId, long groupId) {
        Group foundGroup = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException());

        // find and remove GroupMember entity
        GroupMember gm = groupMemberRepository.findByUser_IdAndGroup_Id(userId, groupId)
                .orElseThrow(() -> new NoSuchElementException());
        groupMemberRepository.delete(gm);

        // create return groupDTO
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.copyFromGroupDO(foundGroup);

        return groupDTO;
    }
}
