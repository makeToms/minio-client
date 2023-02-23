package org.spring.minio.client.factory;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.spring.minio.client.configuration.MinioPoolConfiguration;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 * 连接池配置
 */
public class MinioClientPoolConfig extends GenericObjectPoolConfig {

    public MinioClientPoolConfig(MinioPoolConfiguration configuration) {
        //检查对象是否有效，默认为false（不检查）
        setTestWhileIdle(true);
        //连接池中的连接最小空闲时间(池中最小的空闲时间，空闲半小时后直接销毁)
        setMinEvictableIdleTimeMillis(configuration.getMinEvictableIdleTimeMillis());
        //检查空闲对象和维护连接池的时间间隔。这个参数默认是1分钟。
        setTimeBetweenEvictionRunsMillis(configuration.getTimeBetweenEvictionRunsMillis());
        //每次检查空闲对象的个数，如果小于0，表示检查所有空闲对象。默认是3个。
        setNumTestsPerEvictionRun(configuration.getNumTestsPerEvictionRun());
        setMaxTotal(configuration.getMaxTotal());
        setMinIdle(configuration.getMinIdle());
        setMaxIdle(configuration.getMaxIdle());
    }
}