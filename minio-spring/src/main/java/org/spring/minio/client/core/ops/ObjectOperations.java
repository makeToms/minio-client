package org.spring.minio.client.core.ops;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.minio.client.MinioTemplate;
import org.spring.minio.client.ex.MinioConnectException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 * 定义对象文件的不同操作
 * 可以理解为我们模板方法中在子类中添加的更强方法
 * 但是我们在这里是将这些更强的方法抽离出模板类
 * 这样在每次调用更强的方法时，只需要在模板中进行调用目标
 * 然后在我们的更强的方法实现中进行具体实现即可
 */
public class ObjectOperations implements DefaultObjectOperations {

    Logger log = LoggerFactory.getLogger(MinioClient.class);
    private MinioTemplate minioTemplate;

    public ObjectOperations(MinioTemplate minioTemplate) {
        this.minioTemplate = minioTemplate;
    }

    @Override
    public List<Item> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        return minioTemplate.execute((client -> {
            try {
                List<Item> list = new ArrayList<>();
                Iterable<Result<Item>> objectsIterator = client.listObjects(
                        ListObjectsArgs.builder().bucket(bucketName).prefix(prefix)
                                .recursive(recursive).build()
                );
                if (objectsIterator != null) {
                    Iterator<Result<Item>> iterator = objectsIterator.iterator();
                    if (iterator != null) {
                        while (iterator.hasNext()) {
                            Result<Item> result = iterator.next();
                            Item item = result.get();
                            list.add(item);
                        }
                    }
                }
                return list;
            } catch (Exception e) {
                log.debug("在{}桶中查询前缀为{}失败", bucketName, prefix);
                throw new MinioConnectException("查找失败", e);
            }
        }));
    }

    @Override
    public String generateTemporaryPath(String bucket, String objectName, Method method, Integer time) {
        // 判断minio服务器是否存在bucketName这个桶
        return minioTemplate.execute((client -> {
            try {
                GetPresignedObjectUrlArgs user = GetPresignedObjectUrlArgs.builder().method(method).object(objectName).bucket(bucket).expiry(time).build();
                return client.getPresignedObjectUrl(user);
            } catch (Exception e) {
                log.debug("对{}桶中的{}文件生URL失败", bucket, objectName);
                throw new RuntimeException("链接生成失败", e);
            }
        }));
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) {
        return minioTemplate.execute((client -> {
            try {
                return client.getObject(GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build());
            } catch (Exception e) {
                throw new MinioConnectException("", e);
            }
        }));
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream stream) {
        minioTemplate.execute((client -> {
            try {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Amz-Storage-Class", "REDUCED_REDUNDANCY");
                Map<String, String> userMetadata = new HashMap<>();
                userMetadata.put("My-Project", "Project One");
                client.putObject(
                        PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                        stream, stream.available(), -1)
                                .headers(headers)
                                .userMetadata(userMetadata)
                                .build());
            } catch (Exception e) {
                log.debug("将{}文件上传至{}桶中失败", objectName, bucketName);
                throw new MinioConnectException("上传失败", e);
            }
        }));
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) {
        minioTemplate.execute((client -> {
            try {
                client.putObject(
                        PutObjectArgs.builder().bucket(bucketName)
                                .object(objectName).stream(stream, size, -1)
                                .contentType(contextType).build());
            } catch (Exception e) {
                log.debug("将{}文件上传至{}桶中失败", objectName, bucketName);
                throw new MinioConnectException("上传失败", e);
            }
        }));
    }

    @Override
    public void putObject(String bucketName, String objectName, String fileLocalPath) {
        minioTemplate.execute((client -> {
            try {
                File file = new File(fileLocalPath);
                client.putObject(
                        PutObjectArgs.builder().bucket(bucketName)
                                .object(objectName).stream(new FileInputStream(file), file.length(), -1)
                                .contentType("image/webp").build());
            } catch (Exception e) {
                log.debug("将{}文件上传至{}桶中失败", objectName, bucketName);
                throw new MinioConnectException("上传失败", e);
            }
        }));
    }

    @Override
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) {
        return minioTemplate.execute((client -> {
            try {
                return client.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
            } catch (Exception e) {
                log.debug("获取{}桶中的{}文件信息失败", objectName, bucketName);
                throw new MinioConnectException("获取文件信息失败", e);
            }
        }));
    }

    @Override
    public boolean removeObject(String bucketName, String objectName) {
        return minioTemplate.execute((client -> {
            try {
                client.removeObject(
                        RemoveObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .build());
            } catch (Exception e) {
                log.debug("将{}文件从{}桶中删除", objectName, bucketName);
                throw new MinioConnectException("删除失败", e);
            }
            return true;
        }));
    }
}
