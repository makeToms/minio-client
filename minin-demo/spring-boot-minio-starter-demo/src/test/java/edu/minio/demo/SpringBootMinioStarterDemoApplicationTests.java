package edu.minio.demo;

import org.junit.jupiter.api.Test;
import org.spring.minio.client.MinioTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootMinioStarterDemoApplicationTests {
    @Autowired
    private MinioTemplate minioTemplate;

    @Test
    void contextLoads() {
        minioTemplate.opsForBucket().createBucket("sadass");
    }

}
