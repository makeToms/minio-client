package edu.minio.demo.test;

import org.spring.minio.client.MinioTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
@Service
public class MinioTemplateTest {
    @Autowired
    private MinioTemplate minioTemplate;

    public void sh(){
        minioTemplate.opsForBucket().createBucket("sadass");
    }
}
