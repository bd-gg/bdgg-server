package gg.boardgame.bdgg.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long userId);
    Optional<List<User>> findByNameContainsIgnoreCase(String name);
    List<User> findByIdIn(List<Long> ids);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findBySnsIdAndProvider(String snsId, String provider);

}
