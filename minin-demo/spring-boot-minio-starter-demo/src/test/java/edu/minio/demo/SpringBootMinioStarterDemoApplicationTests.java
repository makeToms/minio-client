package edu.minio.demo;

import org.junit.jupiter.api.Test;
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
        Map<String, Boolean> bucketStrategyName = minioProperties.getBucketStrategyName();
        Set<String> keySet = bucketStrategyName.keySet();
        keySet.forEach(item -> {
            minioTemplate.opsForBucket().createBucket(item);
            if (bucketStrategyName.get(item)) {
                minioTemplate.opsForBucket().setPolicy(item);
            }
        });
    }

}
