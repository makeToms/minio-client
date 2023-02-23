package org.spring.minio.client.configuration;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
public class MinioPoolConfigurationBuilder {


    private MinioPoolConfiguration minioPoolConfiguration;

    public MinioPoolConfigurationBuilder() {
        minioPoolConfiguration = new MinioPoolConfiguration();
    }

    public MinioPoolConfigurationBuilder host(String host) {
        minioPoolConfiguration.setHost(host);
        return this;
    }

    public MinioPoolConfigurationBuilder port(int port) {
        minioPoolConfiguration.setPort(port);
        return this;
    }

    public MinioPoolConfigurationBuilder username(String username) {
        minioPoolConfiguration.setUsername(username);
        return this;
    }

    public MinioPoolConfigurationBuilder password(String password) {
        minioPoolConfiguration.setPassword(password);
        return this;
    }

    public MinioPoolConfigurationBuilder maxTotal(int maxTotal) {
        minioPoolConfiguration.setMaxTotal(maxTotal);
        return this;
    }

    public MinioPoolConfigurationBuilder minIdle(int minIdle) {
        minioPoolConfiguration.setMinIdle(minIdle);
        return this;
    }

    public MinioPoolConfigurationBuilder maxIdle(int maxIdle) {
        minioPoolConfiguration.setMaxIdle(maxIdle);
        return this;
    }

    public MinioPoolConfigurationBuilder whileIdle(boolean whileIdle) {
        minioPoolConfiguration.setWhileIdle(whileIdle);
        return this;
    }

    public MinioPoolConfigurationBuilder minEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        minioPoolConfiguration.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        return this;
    }

    public MinioPoolConfigurationBuilder timeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        minioPoolConfiguration.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        return this;
    }

    public MinioPoolConfigurationBuilder numTestsPerEvictionRun(int numTestsPerEvictionRun) {
        minioPoolConfiguration.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        return this;
    }

    public MinioPoolConfiguration builder() {
        return minioPoolConfiguration;
    }

}
