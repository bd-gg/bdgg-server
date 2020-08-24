package gg.boardgame.bdgg.service;

import com.google.gson.JsonObject;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface GroupService {
    List<Map.Entry<String, Long>> getMatchIds(long id, Pageable pageable) throws ResourceNotFoundException;
}
