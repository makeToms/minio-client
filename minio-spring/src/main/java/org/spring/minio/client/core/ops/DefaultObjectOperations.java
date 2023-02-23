package org.spring.minio.client.core.ops;

import io.minio.StatObjectResponse;
import io.minio.http.Method;
import io.minio.messages.Item;

import java.io.InputStream;
import java.util.List;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 * 对象
 */
public interface DefaultObjectOperations {

    /**
     * 根据文件前缀查询文件
     *
     * @param bucketName
     * @param prefix
     * @param recursive
     * @return
     */
    List<Item> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive);

    /**
     * 生成链接
     *
     * @param bucket     桶名
     * @param objectName 对象名
     * @param method     链接请求方式
     * @param time       时间
     * @return
     * @throws RuntimeException
     */
    String generateTemporaryPath(String bucket, String objectName, Method method, Integer time);

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    InputStream getObject(String bucketName, String objectName);

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    void putObject(String bucketName, String objectName, InputStream stream);

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType);

    /**
     * 上传文件
     *
     * @param bucketName    bucket名称
     * @param objectName    文件名称
     * @param fileLocalPath 文件在本地的路径
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    void putObject(String bucketName, String objectName, String fileLocalPath);

    /**
     * 获取文件信息, 如果抛出异常则说明文件不存在
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    StatObjectResponse getObjectInfo(String bucketName, String objectName);

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
     boolean removeObject(String bucketName, String objectName);
}
