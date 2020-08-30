package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.MatchListDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import gg.boardgame.bdgg.dto.GroupDTO;
import gg.boardgame.bdgg.dto.GroupListDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public MatchListDTO getMatchList(long groupId, Pageable pageable) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("group is not found for this group id:: " + id));
        List<Match> matchList = group.getMatches();

        MatchListDTO resMatchList = new MatchListDTO();
        resMatchList.setItems(matchList);

        return resMatchList;
    }

    @Override
    public GroupDTO createGroup(GroupDTO group, long leaderId) {
        /* no need to check if it already exist
        *  because, group doesn't have unique properties */
        // get users by id
        List<User> users = userRepository.findByIdIn(group.getMembers());
        if(users.size() != group.getMembers().size()) {
            // invalid user-ids exist
            throw new NoSuchElementException();
        }

        Group groupDO = Group.builder()
                .groupName(group.getName())
                .groupImage(group.getImage())
                .groupLeader(leaderId) // it should be driven from user token
                .groupEnterPassword(null)
                .build();

        groupRepository.save(groupDO);
        groupRepository.flush();

        // create GroupMembers table entity
        List<GroupMember> gmList = new ArrayList<>();
        users.stream().forEach(x -> {
            GroupMember gm = new GroupMember();
            gm.changeGroup(groupDO);
            gm.changeUser(x);
            gmList.add(gm);
        });
        groupMemberRepository.saveAll(gmList);

        // set generated id
        group.setId(groupDO.getId());
        return group;
    }

    @Override
    public GroupListDTO getGroupList() {
        List<Group> groupList = groupRepository.findAll();

        GroupListDTO resGroupList = new GroupListDTO();
        List<GroupDTO> items = new ArrayList<>();

        // set groupDTO list
        groupList.stream().forEach(g -> items.add(createGroupDtoFromGroup(g)));
        resGroupList.setItems(items);

        return resGroupList;
    }

    @Override
    public GroupDTO updateGroup(GroupDTO group) {
        Group foundGroup = groupRepository.findById(group.getId()).orElseThrow(() -> new NoSuchElementException());

        // set updating fields
        setOnlyDiffFields(foundGroup, group);

        // update the row
        groupRepository.save(foundGroup);

        // return updated DTO
        GroupDTO resGroup = new GroupDTO();
        resGroup.copyFromGroupDO(foundGroup);
        return resGroup;
    }

    public void setOnlyDiffFields(Group group, GroupDTO groupDTO) {
        if(groupDTO.getImage() != null)
            group.setGroupImage(groupDTO.getImage());
        if(groupDTO.getName() != null)
            group.setGroupName(groupDTO.getName());
        if(groupDTO.getPlace() != null)
            group.setGroupPlace(groupDTO.getPlace());
    }

    public GroupDTO createGroupDtoFromGroup(Group g) {
        // find recently played games(up to 3)
        List<Match> rpgList = matchRepository.findTop3ByGroupId(g.getId()).orElseGet(() -> Collections.EMPTY_LIST);

        // create return groupDTO
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.copyFromGroupDO(g);
        groupDTO.setTotalPlayCount(matchRepository.countByGroupId(g.getId()));
        groupDTO.setRecentlyPlayedGames(rpgList);

        return groupDTO;
    }
}
