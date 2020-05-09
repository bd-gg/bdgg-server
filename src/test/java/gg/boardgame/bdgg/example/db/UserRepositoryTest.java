package gg.boardgame.bdgg.example.db;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void di() throws SQLException {
        User user = new User();
        user.setName("Taehyun");
        user.setPassword("pass");

        User newUser = userRepository.save(user);

        assertThat(newUser).isNotNull();

        Optional<User> existingUser = userRepository.findByName(newUser.getName());
        assertThat(existingUser).isNotEmpty();

        Optional<User> nonExistingUser = userRepository.findByName("whiteship");
        assertThat(nonExistingUser).isEmpty();
    }
}