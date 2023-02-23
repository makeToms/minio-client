package org.spring.minio.client.factory;

import io.minio.MinioClient;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.minio.client.ex.MinioConnectException;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 * 最底层的连接池
 */
public class MinioClientPool<T> implements Closeable {

    Logger log = LoggerFactory.getLogger(MinioClient.class);
    /**
     * GenericObjectPool对象池实现类，管理一组可重用对象
     * 提供对象池的创建、获取、归还、失效、激活等功能
     * 高并发情况下可以避免频繁创建对象的开销，提升性能
     */
    private GenericObjectPool<T> internalPool;

    public MinioClientPool(GenericObjectPoolConfig internalPool, PooledObjectFactory<T> factory) {
        init(internalPool, factory);
    }

    private void init(GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        internalPool = new GenericObjectPool<T>(factory, poolConfig);
    }

    @Override
    public void close() {
        internalPool.close();
    }

    public T getResource() {
        try {
            return internalPool.borrowObject();
        } catch (Exception e) {
            throw new MinioConnectException("获取连接失败", e);
        }
    }

    public void returnResource(T resource) {
        if (resource != null) {
            try {
                internalPool.returnObject(resource);
            } catch (Exception e) {
                log.debug("归还连接失败");
                throw new RuntimeException("return connection failed", e);
            }
        }

    }
}
