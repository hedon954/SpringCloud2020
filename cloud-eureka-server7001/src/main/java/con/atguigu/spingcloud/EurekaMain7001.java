package con.atguigu.spingcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Hedon Wang
 * @create 2020-04-25 09:38
 */

@SpringBootApplication
@EnableEurekaServer   //表明是服务注册中心的Server端
public class EurekaMain7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }

}
