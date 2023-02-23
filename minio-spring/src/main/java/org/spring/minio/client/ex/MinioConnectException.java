package org.spring.minio.client.ex;

import lombok.Getter;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 * 异常
 */
@Getter
public class MinioConnectException extends RuntimeException {

    private Integer code;

    public MinioConnectException(Integer code) {
        this.code = code;
    }

    public MinioConnectException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public MinioConnectException(String message, Throwable e) {
        super(message, e);
    }
}
