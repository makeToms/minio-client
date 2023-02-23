package org.spring.minio.client;

import io.minio.MinioClient;
import org.spring.minio.client.core.callback.OpsCallback;
import org.spring.minio.client.core.callback.VoidOpsCallback;
import org.spring.minio.client.core.ops.BucketOperations;
import org.spring.minio.client.core.ops.DefaultBucketOperations;
import org.spring.minio.client.core.ops.ObjectOperations;
import org.spring.minio.client.factory.MinioClientAccessor;
import org.spring.minio.client.factory.MinioClientFactory;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/20
 */
public class MinioTemplate extends MinioClientAccessor {


    /**
     * 向父类传递minio的工厂
     *
     * @param factory
     */
    public MinioTemplate(MinioClientFactory factory) {
        super(factory);
    }


    /**
     * ops桶模板方法
     *
     * @return
     */
    public DefaultBucketOperations opsForBucket() {
        return new BucketOperations(this);
    }

    /**
     * ops对象模板方法
     *
     * @return
     */
    public ObjectOperations opsForObject() {
        return new ObjectOperations(this);
    }

    /**
     * 执行模板-带返回值
     */
    public <T> T execute(OpsCallback<T> callback) {
        // 获取链接对象
        MinioClient client = getFactory().getResource();
        // 调用目标方法
        T t = callback.callback(client);
        // 返回
        getFactory().returnResource(client);
        return t;
    }

    /**
     * 执行模板-带返回值
     */
    public void execute(VoidOpsCallback callback) {
        // 获取链接对象
        MinioClient client = getFactory().getResource();
        // 调用目标方法
        callback.callback(client);
        // 返回
        getFactory().returnResource(client);
    }

}
