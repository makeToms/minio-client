package org.spring.minio.client.core.ops;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.minio.client.MinioTemplate;
import org.spring.minio.client.ex.MinioConnectException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
public class BucketOperations implements DefaultBucketOperations {

    Logger log = LoggerFactory.getLogger(MinioClient.class);


    private MinioTemplate minioTemplate;

    public BucketOperations(MinioTemplate minioTemplate) {
        this.minioTemplate = minioTemplate;
    }

    @Override
    public boolean createBucket(String bucketName) {
        return minioTemplate.execute((client) -> {
            try {
                if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                    client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                    return true;
                }
                return false;
            } catch (Exception e) {
                throw new MinioConnectException("桶创建失败", e);
            }
        });
    }


    /**
     * 获取所有桶
     *
     * @return
     */
    @Override
    public List<Bucket> getAllBuckets() {
        // 列出所有存储桶
        return minioTemplate.execute((client -> {
            try {
                return client.listBuckets();
            } catch (Exception e) {
                throw new MinioConnectException("获取失败", e);
            }
        }));
    }

    /**
     * 根据桶名获取桶信息
     *
     * @param bucketName
     * @return
     */
    @Override
    public Optional<Bucket> getBucketByBucketName(String bucketName) {
        return minioTemplate.execute((client) -> {
            try {
                return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
            } catch (Exception e) {
                throw new MinioConnectException("获取桶信息失败", e);
            }
        });
    }

    /**
     * 删除桶
     *
     * @param bucketName
     */
    @Override
    public void removeBucket(String bucketName) {
        minioTemplate.execute((client) -> {
            try {
                // 删除之前先检查bucket是否存在。
                boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
                if (found) {
                    // 删除指定的存储桶，注意，只有存储桶为空时才能删除成功。
                    client.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
                } else {
                    log.debug(bucketName + "桶不存在");
                }
            } catch (Exception e) {
                log.debug(bucketName + "删除信息失败: ", e);
            }
        });
    }

    @SneakyThrows
    @Override
    public void setPolicy(String version, String bucketNamePolicy) {
        String policy = "{\"Version\": \"2012-10-17\",\"Statement\": [{\"Action\": [\"s3:GetObject\"],\"Effect\": \"Allow\",\"Principal\": {\"AWS\": [\"*\"]},\"Resource\": [\"arn:aws:s3:::" + bucketNamePolicy + "]}]}";
        minioTemplate.execute(client -> {
            try {
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketNamePolicy).config(policy).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
