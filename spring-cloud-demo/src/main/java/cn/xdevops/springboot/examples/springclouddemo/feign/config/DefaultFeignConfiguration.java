package cn.xdevops.springboot.examples.springclouddemo.feign.config;


import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultFeignConfiguration {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient.Builder().build();
    }
}
