package com.example.simple;

import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SimpleApplicationTests {

	Logger log = LoggerFactory.getLogger(SimpleApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	JdbcClient jdbcClient;

	@Autowired
	DataSource dataSource;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute("delete from customer");
	}

	@Test
	void contextLoads() {
	}

	@Test
	void test_get_data() {
		jdbcTemplate.execute("insert into customer(name) values ('Josh')");

		Integer result = jdbcTemplate.queryForObject("select count(*) from customer", Integer.class);

		log.info("result: {}", result);


//		jdbcClient
//				.sql("select * from customer")
//				.query(Customer.class)
//				.stream()
//				.forEach(System.out::println);

	}

	@Test
	void inspectDS() throws SQLException {
		DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();
		String url = databaseMetaData.getURL();
		log.info("url: {}", url);
	}
}
