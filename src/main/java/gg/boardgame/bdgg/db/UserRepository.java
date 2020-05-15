package gg.boardgame.bdgg.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findById(long userId);
    public Optional<User> findByName(String name);
    public Optional<User> findByEmail(String email);

}
