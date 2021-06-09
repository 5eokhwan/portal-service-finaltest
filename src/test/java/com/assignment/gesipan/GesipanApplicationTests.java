package com.assignment.gesipan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GesipanApplicationTests {

    @Test
    void contextLoads() throws Exception {
        mysqlRunner mysqlrunner  = new mysqlRunner();
        mysqlrunner.run(null);
    }

}
