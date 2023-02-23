package test.minio;

import org.minio.demo.configuation.MinioConfiguration;
import org.spring.minio.client.MinioTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/2/23
 */
public class MinioTemplateTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MinioConfiguration.class);
        MinioTemplate template = context.getBean(MinioTemplate.class);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                boolean sbss = template.opsForBucket().createBucket("sbss");
                System.out.println(sbss);
            });
        }
    }

}
