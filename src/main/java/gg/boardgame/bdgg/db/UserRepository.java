package gg.boardgame.bdgg.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long userId);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

}
