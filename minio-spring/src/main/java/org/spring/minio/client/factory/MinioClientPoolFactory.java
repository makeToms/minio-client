package org.spring.minio.client.factory;

import io.minio.MinioClient;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.minio.client.configuration.MinioPoolConfiguration;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 * 连接池工厂
 */
public class MinioClientPoolFactory implements PooledObjectFactory<MinioClient> {

    /**
     * http协议
     */
    public static final String HTTP_PROTOCOL = "http://";

    Logger log = LoggerFactory.getLogger(MinioClient.class);

    private MinioPoolConfiguration minioPoolConfiguration;

    /**
     * 初始化配置信息
     *
     * @param configuration
     */
    public MinioClientPoolFactory(MinioPoolConfiguration configuration) {
        this.minioPoolConfiguration = configuration;
    }

    /**
     * 借出实例
     *
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<MinioClient> makeObject() throws Exception {
        MinioClient client = buildMinioClient(minioPoolConfiguration);
        PooledObject<MinioClient> minioClientPooledObject = new DefaultPooledObject<>(client);
        return minioClientPooledObject;
    }

    /**
     * 销毁实例
     *
     * @param p a {@code PooledObject} wrapping the instance to be destroyed
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<MinioClient> p) throws Exception {

    }

    /**
     * 验证对象
     * <p>
     * 检测一个对象是否有效,无效会被销毁
     *
     * @param p a {@code PooledObject} wrapping the instance to be validated
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<MinioClient> p) {
        log.debug("认证");
        return true;
    }

    /**
     * 激活一个对象或者说启动对象的某些操作
     *
     * @param p a {@code PooledObject} wrapping the instance to be activated
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<MinioClient> p) throws Exception {
        log.debug("激活对象");
    }

    /**
     * 在向对象池归还一个对象是会调用这个方法。这里可以对对象做一些清理操作。比如清理掉过期的数据，下次获得对象时，不受旧数据的影响
     *
     * @param p a {@code PooledObject} wrapping the instance to be passivated
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<MinioClient> p) throws Exception {
        log.debug("归还之前");
    }

    /**
     * 构建minio连接对象
     *
     * @param configuration
     * @return
     */
    private MinioClient buildMinioClient(MinioPoolConfiguration configuration) {
        return MinioClient.
                builder().
                endpoint(gainHostAddress(configuration.getHost(),configuration.getPort())).
                credentials(configuration.getUsername(), configuration.getUsername()).
                build();
    }

    /**
     * 转换成目标主机连接地址
     * @param host
     * @param port
     * @return
     */
    private String gainHostAddress(String host, int port) {
        return HTTP_PROTOCOL + host + ":" + port;
    }

}
