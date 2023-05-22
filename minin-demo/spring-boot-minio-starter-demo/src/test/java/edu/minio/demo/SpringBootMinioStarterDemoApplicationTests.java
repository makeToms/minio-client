package edu.minio.demo;

import org.junit.jupiter.api.Test;
import org.spring.boot.autoconfigure.properties.BucketProperties;
import org.spring.boot.autoconfigure.properties.MinioProperties;
import org.spring.minio.client.MinioTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Set;

@SpringBootTest
class SpringBootMinioStarterDemoApplicationTests {
    @Autowired
    private MinioTemplate minioTemplate;
    @Autowired
    private MinioProperties minioProperties;

    @Test
    void contextLoads() {
        Map<String, BucketProperties> bucketStrategyName = minioProperties.getBucketStrategyName();
        bucketStrategyName.forEach((item, value) -> {
            minioTemplate.opsForBucket().createBucket(value.getBucketName());
            if (value.getPublicValue()) {
                minioTemplate.opsForBucket().setPolicy(value.getBucketName());
            }
        });
    }

}
