package gg.boardgame.bdgg.service;

import com.google.gson.JsonObject;
import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Map.Entry<String,Long>> getMatchIds(long id, Pageable pageable) throws ResourceNotFoundException {
        List<Map.Entry<String,Long>> matchIds = new ArrayList<>();

        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("group is not found for this group id:: " + id));

        group.getMatches().forEach((Match)->{
            matchIds.add(new AbstractMap.SimpleEntry<>("matchId",Match.getId()));
        });

        return matchIds;
    }
}
