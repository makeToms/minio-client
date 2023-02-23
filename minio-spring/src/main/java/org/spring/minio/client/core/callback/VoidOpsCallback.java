package org.spring.minio.client.core.callback;

import io.minio.MinioClient;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
@FunctionalInterface
public interface VoidOpsCallback {

    /**
     * 执行
     *
     * @param client
     * @return
     */
     void callback(MinioClient client);

}
