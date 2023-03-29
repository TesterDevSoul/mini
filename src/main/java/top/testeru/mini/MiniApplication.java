package top.testeru.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/***
 * springboot工程中都有一个启动引导类，这是工程入口类
 * 并在引导类上添加@SpringBootApplication
 * 扫描当前类及其子类
 */
@SpringBootApplication
@MapperScan("top.testeru.mini.dao")
//启动引导类
public class MiniApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniApplication.class, args);
    }
}
