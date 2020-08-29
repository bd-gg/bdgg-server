package gg.boardgame.bdgg.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    long countByGroupId(long groupId);
    Optional<List<Match>> findTop3ByGroupId(long groupId);
}
