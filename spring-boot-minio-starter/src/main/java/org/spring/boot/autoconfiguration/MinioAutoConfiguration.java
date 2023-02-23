package org.spring.boot.autoconfiguration;

import io.minio.MinioClient;
import okhttp3.HttpUrl;
import org.spring.boot.autoconfiguration.properties.MinioProperties;
import org.spring.minio.client.MinioTemplate;
import org.spring.minio.client.configuration.MinioPoolConfiguration;
import org.spring.minio.client.configuration.MinioPoolConfigurationBuilder;
import org.spring.minio.client.factory.MinioClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/20
 */
@Configuration
@ConditionalOnClass(value = {MinioClient.class, MinioClientFactory.class, HttpUrl.class})
@EnableConfigurationProperties(value = MinioProperties.class)
public class MinioAutoConfiguration {
    @Autowired
    private MinioProperties minioProperties;

    @Bean
    @ConditionalOnMissingBean
    public MinioClientFactory minioClientFactory() {
        MinioPoolConfiguration builder = new MinioPoolConfigurationBuilder()
                .username(minioProperties.getUsername())
                .host(minioProperties.getHost())
                .port(minioProperties.getPort())
                .password(minioProperties.getPassword())
                .maxIdle(minioProperties.getMaxIdle())
                .minIdle(minioProperties.getMinIdle())
                .maxTotal(minioProperties.getMaxTotal())
                .numTestsPerEvictionRun(minioProperties.getNumTestsPerEvictionRun())
                .timeBetweenEvictionRunsMillis(minioProperties.getTimeBetweenEvictionRunsMillis())
                .whileIdle(minioProperties.isWhileIdle())
                .minEvictableIdleTimeMillis(minioProperties.getMinEvictableIdleTimeMillis())
                .builder();
        MinioClientFactory factory = new MinioClientFactory(builder);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    public MinioTemplate minioTemplate(MinioClientFactory minioClientFactory) {
        return new MinioTemplate(minioClientFactory);
    }

}
