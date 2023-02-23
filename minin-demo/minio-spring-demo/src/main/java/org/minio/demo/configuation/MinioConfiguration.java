package org.minio.demo.configuation;

import org.spring.minio.client.MinioTemplate;
import org.spring.minio.client.configuration.MinioPoolConfiguration;
import org.spring.minio.client.configuration.MinioPoolConfigurationBuilder;
import org.spring.minio.client.factory.MinioClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
@Configuration
public class MinioConfiguration {

    @Bean
    public MinioClientFactory minioClientFactory() {
        MinioPoolConfiguration builder = new MinioPoolConfigurationBuilder()
                .username("minioadmin")
                .host("127.0.0.1")
                .port(9000)
                .password("minioadmin")
                .maxIdle(10)
                .minIdle(4)
                .maxTotal(15)
                .numTestsPerEvictionRun(4000)
                .timeBetweenEvictionRunsMillis(4000)
                .whileIdle(false)
                .minEvictableIdleTimeMillis(5000)
                .builder();
        MinioClientFactory factory = new MinioClientFactory(builder);
        return factory;
    }

    @Bean
    public MinioTemplate minioTemplate(MinioClientFactory minioClientFactory) {
        return new MinioTemplate(minioClientFactory);
    }

}
