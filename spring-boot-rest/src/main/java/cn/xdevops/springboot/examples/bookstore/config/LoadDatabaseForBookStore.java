package cn.xdevops.springboot.examples.bookstore.config;

import cn.xdevops.springboot.examples.bookstore.repository.BookRepository;
import cn.xdevops.springboot.examples.bookstore.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Slf4j
public class LoadDatabaseForBookStore {
    @Bean
    CommandLineRunner initDatabaseForBookStore(BookRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Book("Domain-Driven Design Distilled", "Vaughn Vernon", new BigDecimal("65.00"))));
            log.info("Preloading " + repository.save(new Book("Kubernetes: Up & Running", "Kelsey Hightower", new BigDecimal("58.00"))));
        };
    }
}
