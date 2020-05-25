package gg.boardgame.bdgg.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameHistoryRepository extends JpaRepository<UserGameHistory, Long> {
     Page<UserGameHistory> findByUser_Id(long userId, Pageable pageable);
}
