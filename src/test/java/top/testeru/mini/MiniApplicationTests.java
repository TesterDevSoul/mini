package top.testeru.mini;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * SpringJUnit4ClassRunner.class:Spring运行环境
 * JUnit4.class:JUnit运行环境
 * SpringRunner.class:Spring Boot运行环境
 */
//@RunWith(SpringRunner.class) //@RunWith:运行器
//标记为当前类为SpringBoot测试类，加载项目的ApplicationContext上下文环境
@SpringBootTest
class MiniApplicationTests {

    /**
     * 需求:调用UserController的get方法
     *
     */
    @Test
    void contextLoads() {

    }

}
