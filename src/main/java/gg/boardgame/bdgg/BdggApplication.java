package gg.boardgame.bdgg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@SpringBootApplication
public class BdggApplication implements CommandLineRunner {

	@Autowired
	DataSource dataSource;

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
	}
}
