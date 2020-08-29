package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.dto.GameHistoryDTO;
import gg.boardgame.bdgg.dto.GroupDTO;
import gg.boardgame.bdgg.dto.GroupListDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGameHistoryRepository userGameHistoryRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupService groupService;

    @Override
    public ProfileDTO getProfile(String userName, Pageable pageable) throws ResourceNotFoundException {

        User user = userRepository.findByName(userName).orElseThrow(() -> new ResourceNotFoundException("user is not found for this name:: " + userName));

        long userId = user.getId();
        Page<UserGameHistory> userGameHistories = userGameHistoryRepository.findByUser_Id(userId, pageable);

        ProfileDTO profile = new ProfileDTO(user);

        userGameHistories.getContent().forEach(u -> {
            GameHistoryDTO item = new GameHistoryDTO();
            item.setGameId(u.getGameId());
            item.setCount(u.getCount());
            profile.getMostPlayed().add(item);
        });

        return profile;
    }

    @Override
    public GroupDTO joinGroup(long userId, long groupId) {
        Optional<GroupMember> foundGM = groupMemberRepository.findByUser_IdAndGroup_Id(userId, groupId);

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Group foundGroup = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException());

        // create GroupMember entity
        GroupMember gm = new GroupMember();
        gm.changeUser(foundUser);
        gm.changeGroup(foundGroup);
        groupMemberRepository.save(gm);

        // create and return groupDTO
        return groupService.createGroupDtoFromGroup(foundGroup);
    }

    @Override
    public GroupListDTO getJoinedGroups(long userId) {
        List<GroupMember> groupMemberList = groupMemberRepository.findByUser_id(userId).orElseGet(() -> Collections.EMPTY_LIST);

        // create GroupList
        GroupListDTO groupList = new GroupListDTO();
        List<GroupDTO> items = new ArrayList<>();

        // set groupDTO list
        groupMemberList.stream().forEach(gm -> items.add(groupService.createGroupDtoFromGroup(gm.getGroup())));
        groupList.setItems(items);

        return groupList;
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
