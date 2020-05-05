package gg.boardgame.bdgg;

import gg.boardgame.bdgg.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BdggApplication implements CommandLineRunner{

	@Autowired
	TestMapper testMapper;

	public static void main(String[] args) {
		SpringApplication.run(BdggApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("User:" + testMapper.getUser());
	}
}
