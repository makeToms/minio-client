package test;

import io.minio.http.Method;
import org.spring.minio.client.MinioTemplate;
import org.spring.minio.client.configuration.MinioPoolConfiguration;
import org.spring.minio.client.configuration.MinioPoolConfigurationBuilder;
import org.spring.minio.client.factory.MinioClientFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
public class MinioClientTest {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        testPool();

    }

    public static void testPool() {
        MinioPoolConfiguration builder = new MinioPoolConfigurationBuilder()
                .username("minioadmin")
                .host("127.0.0.1")
                .port(9000)
                .password("minioadmin")
                .maxIdle(10)
                .minIdle(4)
                .maxTotal(15)
                .numTestsPerEvictionRun(4000)
                .timeBetweenEvictionRunsMillis(4000)
                .whileIdle(false)
                .minEvictableIdleTimeMillis(5000)
                .builder();
        MinioClientFactory factory = new MinioClientFactory(builder);
        MinioTemplate template = new MinioTemplate(factory);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                boolean nfit = template.opsForBucket().createBucket("nfit");
                System.out.println(nfit);
            });
        }
    }
}
