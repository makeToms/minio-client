package org.spring.boot.autoconfiguration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/20
 */
@ConfigurationProperties(prefix = "spring.io.minio")
public class MinioProperties {

    /**
     * 主机IP
     */
    private String host = "127.0.0.1";
    /**
     * 端口
     */
    private int port = 9000;
    /**
     * minioadmin
     */
    private String username = "minioadmin";
    /**
     * minioadmin
     */
    private String password = "minioadmin";
    /***********以下为连接池操作**********/
    /**
     * 对象池中管理的最多对象个数。默认值是8
     */
    private int maxTotal = -1;
    /**
     * 对象池中最小的空闲对象个数。默认值是0
     */
    private int minIdle = 10;
    /**
     * 对象池中最大的空闲对象个数。默认值是8
     */
    private int maxIdle = 10;
    /**
     * 检查对象是否有效，默认为false（不检查）
     */
    private boolean whileIdle = false;
    /**
     * 连接池中的连接最小空闲时间(池中最小的空闲时间，空闲半小时后直接销毁)
     */
    private int minEvictableIdleTimeMillis = 30000;
    /**
     * 检查空闲对象和维护连接池的时间间隔。这个参数默认是1分钟。
     */
    private int timeBetweenEvictionRunsMillis = 6000;
    /**
     * 每次检查空闲对象的个数，如果小于0，表示检查所有空闲对象。默认是3个
     */
    private int numTestsPerEvictionRun = 3;
    /**
     * 桶名
     */
    List<String> buckets;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public boolean isWhileIdle() {
        return whileIdle;
    }

    public void setWhileIdle(boolean whileIdle) {
        this.whileIdle = whileIdle;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public List<String> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<String> buckets) {
        this.buckets = buckets;
    }

}
