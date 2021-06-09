package com.assignment.gesipan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
@Component
public class mysqlRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 기본 jdbc api
        try(Connection connection = dataSource.getConnection()) {

            // db 정보 출력
            System.out.println(dataSource.getClass()); // DBCP 정보
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            // USER 테이블 생성
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        }

        // 기본적인 jdbc api 사용보다 좀 더 편하다.
        jdbcTemplate.execute("INSERT INTO USER VALUES (1, 'junseo')");
    }
}
