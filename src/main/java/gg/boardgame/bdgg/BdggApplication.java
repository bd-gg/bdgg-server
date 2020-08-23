package gg.boardgame.bdgg;

import gg.boardgame.bdgg.db.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.ManyToOne;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@SpringBootApplication
public class BdggApplication implements CommandLineRunner {

	@Autowired
	DataSource dataSource;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserGameHistoryRepository userGameHistoryRepository;

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	GroupMemberRepository groupMemberRepository;

	@Autowired
	UserMatchRepository userMatchRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BdggApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try(Connection connection = dataSource.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println(metaData.getURL());
			System.out.println(metaData.getDriverName());
			System.out.println(metaData.getUserName());
		}

		User taehyun = User.builder()
				.name("TaehyunHwang")
				.password(passwordEncoder.encode("pass1"))
				.email("eeht1717@gmail.com")
				.imageUrl("https://imgur.com/Tp6WyNv.png")
				.provider("init")
				.snsId("1").build();

		User jaewon = User.builder()
				.name("JawonKim")
				.password(passwordEncoder.encode("pass2"))
				.email("huhu420@naver.com")
				.imageUrl("https://imgur.com/Tp6WyNv.png")
				.provider("init")
				.snsId("2").build();

		userRepository.save(taehyun);
		userRepository.save(jaewon);

		UserGameHistory gameHistoryOne = UserGameHistory.builder().id(1).count(80).build();
		gameHistoryOne.changeUser(taehyun);
		UserGameHistory gameHistoryTwo = UserGameHistory.builder().id(2).count(100).build();
		gameHistoryTwo.changeUser(taehyun);

		userGameHistoryRepository.save(gameHistoryOne);
		userGameHistoryRepository.save(gameHistoryTwo);

		Group groupOne = Group.builder().id(1).groupName("bdgg_group").groupPlace("Bundang").groupEnterPassword("1234").groupLeader(1).build();

		groupRepository.save(groupOne);

		String timestamp = "2020-05-10 10:20:30.0";
		java.sql.Timestamp time = java.sql.Timestamp.valueOf(timestamp);
		Match macthOne = Match.builder().id(1).gameId(203828).gameType(1).playedTime(time).build();
		macthOne.changeGroup(groupOne);

		matchRepository.save(macthOne);

		UserMatch userMatchOne = UserMatch.builder().id(1).score(80).build();
		userMatchOne.changeUser(taehyun);
		userMatchOne.changeMatch(macthOne);
		userMatchRepository.save(userMatchOne);

		GroupMember groupMemberOne = GroupMember.builder().id(1).build();
		groupMemberOne.changeUser(taehyun);
		groupMemberOne.changeGroup(groupOne);

		groupMemberRepository.save(groupMemberOne);


	}
}
