package gg.boardgame.bdgg.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMatchRepository extends JpaRepository<UserMatch, Long> {
    public Page<UserMatch> findByUser_Id(long userId, Pageable pageable);
    public Page<UserMatch> findByUser_IdAndMatch_GameNo(long userId, int gameNo, Pageable pageable);
    public Page<UserMatch> findByUser_IdAndMatch_GameType(long userId, int gameType, Pageable pageable);
    public Page<UserMatch> findByUser_IdAndMatch_GameNoAndMatch_GameType(
            long userId, int gameNo, int gameType, Pageable pageable);
    public Page<UserMatch> findByMatch_Id(long matchId, Pageable pageable);
}
