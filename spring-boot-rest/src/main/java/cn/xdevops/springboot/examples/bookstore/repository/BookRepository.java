package cn.xdevops.springboot.examples.bookstore.repository;

import cn.xdevops.springboot.examples.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
