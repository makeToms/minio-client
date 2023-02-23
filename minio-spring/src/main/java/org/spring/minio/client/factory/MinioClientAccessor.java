package org.spring.minio.client.factory;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 * 连接对象的访问器
 * <p>
 * 简单理解：我们的template通过该客户端访问器进行获取连接对象
 */
public class MinioClientAccessor {

    private MinioClientFactory factory;

    public MinioClientAccessor(MinioClientFactory factory) {
        this.factory = factory;
    }

    public void setFactory(MinioClientFactory factory) {
        this.factory = factory;
    }

    public MinioClientFactory getFactory() {
        return factory;
    }
}
