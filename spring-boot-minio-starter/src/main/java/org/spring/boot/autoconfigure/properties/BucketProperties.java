package org.spring.boot.autoconfigure.properties;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/5/22
 */
public class BucketProperties {
    /**
     * 桶名
     */
    private String bucketName;
    /**
     * 是否公开
     */
    private Boolean publicValue;

    public BucketProperties() {
    }

    public BucketProperties(String bucketName, Boolean publicValue) {
        this.bucketName = bucketName;
        this.publicValue = publicValue;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Boolean getPublicValue() {
        return publicValue;
    }

    public void setPublicValue(Boolean publicValue) {
        this.publicValue = publicValue;
    }
}
