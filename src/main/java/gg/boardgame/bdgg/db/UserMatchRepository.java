package gg.boardgame.bdgg.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMatchRepository extends JpaRepository<UserMatch, Long> {

}
