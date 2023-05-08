package org.spring.boot.autoconfigure.enums;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2022/10/24
 * ProjectName pandas-util
 * @description：异常枚举
 * @Version 1.0
 */
public enum ExceptionEnum {

    /**
     * 序列化异常
     */
    SERIALIZATION_ERROR(10100, "序列化错误", "serialization is error!"),
    /**
     * 反序列化异常
     */
    DESERIALIZE_ERROR(10101, "反序列化错误", "deserialize is error!"),
    /**
     * 文件类型错误
     */
    FILE_TYPE_ERROR(10102, "文件类型错误", "file type is error!");

    private ExceptionEnum(Integer code, String explain, String message) {
        this.code = code;
        this.explain = explain;
        this.message = message;
    }

    /**
     * 异常代码
     */
    private Integer code;
    /**
     * 异常说明
     */
    private String explain;
    /**
     * 回显信息
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getExplain() {
        return explain;
    }

    public String getMessage() {
        return message;
    }
}
