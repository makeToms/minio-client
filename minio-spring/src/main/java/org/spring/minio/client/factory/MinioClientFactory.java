package org.spring.minio.client.factory;

import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.minio.client.configuration.MinioPoolConfiguration;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 * 暴露给用户进行配置
 * <p>
 * 同时初始化连接池
 */
public class MinioClientFactory {
    Logger log = LoggerFactory.getLogger(MinioClient.class);
    /**
     * 核心连接池
     */
    public MinioClientPool<MinioClient> pool;

    /**
     * 初始化
     *
     * @param minioPoolConfiguration
     */
    public MinioClientFactory(MinioPoolConfiguration minioPoolConfiguration) {
        createPool(minioPoolConfiguration);
    }

    /**
     * 创建连接池
     */
    private void createPool(MinioPoolConfiguration minioPoolConfiguration) {
        MinioClientPoolFactory factory = new MinioClientPoolFactory(minioPoolConfiguration);
        MinioClientPoolConfig poolConfig = getPoolConfig(minioPoolConfiguration);
        pool = new MinioClientPool<>(poolConfig, factory);
    }

    /**
     * 获取一个连接
     *
     * @return
     */
    public MinioClient getResource() {
        try {
            return pool.getResource();
        } catch (Exception e) {
            log.debug("获取连接失败",e);
            throw new RuntimeException("failed to get connection.");
        }
    }

    /**
     * 将连接对象返回池中
     *
     * @param minioClient
     */
    public void returnResource(MinioClient minioClient) {
        pool.returnResource(minioClient);
    }


    /**
     * 连接池配置项
     * <p>
     * todo：后续交给同一个配置类进行配置
     *
     * @param configuration
     * @return
     */
    private MinioClientPoolConfig getPoolConfig(MinioPoolConfiguration configuration) {
        MinioClientPoolConfig poolConfig = new MinioClientPoolConfig(configuration);
        return poolConfig;
    }

}
