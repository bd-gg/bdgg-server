package gg.boardgame.bdgg.example.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMatchRepository extends JpaRepository<UserMatch, Long> {

}
