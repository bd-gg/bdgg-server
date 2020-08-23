package gg.boardgame.bdgg.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMatchRepository extends JpaRepository<UserMatch, Long> {
    Optional<Page<UserMatch>> findByUser_Id(long userId, Pageable pageable);
    Optional<Page<UserMatch>> findByUser_IdAndMatch_GameNo(long userId, int gameNo, Pageable pageable);
    Optional<Page<UserMatch>> findByUser_IdAndMatch_GameType(long userId, int gameType, Pageable pageable);
    Optional<Page<UserMatch>> findByUser_IdAndMatch_GameNoAndMatch_GameType(
            long userId, int gameNo, int gameType, Pageable pageable);
    Optional<Page<UserMatch>> findByMatch_Id(long matchId, Pageable pageable);
}
