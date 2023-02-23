package edu.minio.demo;

import edu.minio.demo.test.MinioTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootMinioStarterDemoApplicationTests {

    @Autowired
    MinioTemplateTest minioTemplateTest;

    @Test
    void contextLoads() {
        minioTemplateTest.sh();
    }

}
