package com.rootlab.photogram;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void multipartDefaultLocationTest() {
        // https://gofnrk.tistory.com/36
        // spring.servlet.multipart.location
        String location = System.getProperty("java.io.tmpdir");
        System.out.println("multipart temp location = " + location);
    }

}
