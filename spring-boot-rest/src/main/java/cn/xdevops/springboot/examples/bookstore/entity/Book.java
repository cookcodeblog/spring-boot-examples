package cn.xdevops.springboot.examples.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String bookName;
    private String author;
    private BigDecimal price;

    public Book(String bookName, String author, BigDecimal price) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }
}
