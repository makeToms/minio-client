package org.spring.minio.client.core.ops;

import io.minio.messages.Bucket;

import java.util.List;
import java.util.Optional;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
public interface DefaultBucketOperations {
    /**
     * 创建桶
     *
     * @param bucketName
     * @return
     */
    boolean createBucket(String bucketName);

    /**
     * 获取所有桶
     *
     * @return
     */
    List<Bucket> getAllBuckets();

    /**
     * 根据桶名获取桶信息
     *
     * @param bucketName
     * @return
     */
    public Optional<Bucket> getBucketByBucketName(String bucketName);

    /**
     * 删除桶
     *
     * @param bucketName
     */
    void removeBucket(String bucketName);

    /**
     * 为桶设置策略
     *
     * @param version          策略唯一标识
     * @param bucketNamePolicy 桶名策略，例如:temp-bucket/*
     */
    void setPolicy(String version, String bucketNamePolicy);
}
