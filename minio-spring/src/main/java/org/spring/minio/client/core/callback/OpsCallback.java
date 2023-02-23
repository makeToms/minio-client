package org.spring.minio.client.core.callback;

import io.minio.MinioClient;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
public interface OpsCallback<T> {

    /**
     * 执行
     *
     * @param client
     * @param <T>
     * @return
     */
     T callback(MinioClient client);

}
