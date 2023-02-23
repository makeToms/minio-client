package org.spring.boot.autoconfiguration.ex;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/20
 * 自定义基础异常
 */
public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(Integer code) {
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
