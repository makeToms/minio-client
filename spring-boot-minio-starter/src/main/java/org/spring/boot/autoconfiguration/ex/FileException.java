package org.spring.boot.autoconfiguration.ex;

import org.spring.boot.autoconfiguration.enums.ExceptionEnum;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/20
 * 文件异常
 */
public class FileException extends BaseException {


    public FileException(ExceptionEnum e) {
        super(e.getMessage(), e.getCode());
    }
}
