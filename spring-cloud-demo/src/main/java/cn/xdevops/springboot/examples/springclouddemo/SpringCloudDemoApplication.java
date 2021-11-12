package cn.xdevops.springboot.examples.springclouddemo;

import cn.xdevops.springboot.examples.springclouddemo.feign.config.DefaultFeignConfiguration;
import feign.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
public class SpringCloudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDemoApplication.class, args);
    }

}
