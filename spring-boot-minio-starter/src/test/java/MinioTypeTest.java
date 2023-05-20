import org.spring.boot.autoconfigure.enums.MinioTypeEnum;

/**
 * @Author make_
 * @email make_toms@hotmail.com
 * @Date 2023/5/20
 */
public class MinioTypeTest {
    public static void main(String[] args) {
        String suffix = MinioTypeEnum.getSuffix("image/jpeg");
        System.out.println(suffix);
    }
}
