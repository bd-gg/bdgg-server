package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gg.boardgame.bdgg.db.Group;
import gg.boardgame.bdgg.db.GroupRepository;
import gg.boardgame.bdgg.dto.GroupDTO;
import gg.boardgame.bdgg.dto.GroupListDTO;
import lombok.extern.slf4j.Slf4j;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Map.Entry<String,Long>> getMatchIds(long id, Pageable pageable) throws ResourceNotFoundException {
        List<Map.Entry<String, Long>> matchIds = new ArrayList<>();

        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("group is not found for this group id:: " + id));

        group.getMatches().forEach((Match) -> {
            matchIds.add(new AbstractMap.SimpleEntry<>("matchId", Match.getId()));
        });

        return matchIds;
    }

    @Override
    public GroupDTO createGroup(GroupDTO group, long leaderId) {
        /* no need to check if it already exist
        *  because, group doesn't have unique properties */

        Group groupDO = Group.builder()
                .groupName(group.getName())
                .groupImage(group.getImage())
                .groupLeader(leaderId) // it should be driven from user token
                .groupEnterPassword(null)
                .build();

        groupRepository.save(groupDO);
        groupRepository.flush();

        // set generated id
        group.setId(groupDO.getId());
        return group;
    }

    @Override
    public GroupListDTO getGroupList() {
        List<Group> groupList = groupRepository.findAll();

        GroupListDTO resGroupList = new GroupListDTO();
        resGroupList.setItems(groupList);

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
}
