package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.dto.GameHistoryDTO;
import gg.boardgame.bdgg.dto.IndividualGameResultDTO;
import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Long> getMatchIds(long id, Pageable pageable) throws ResourceNotFoundException {
        List<Long> matchIds = new ArrayList<>();

        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("group is not found for this group id:: " + id));

        group.getMatches().forEach((Match)->{
            matchIds.add(Match.getId());
        });

        return matchIds;
    }
}
