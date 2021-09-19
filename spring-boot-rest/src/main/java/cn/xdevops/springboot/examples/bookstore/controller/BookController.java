package cn.xdevops.springboot.examples.bookstore.controller;

import cn.xdevops.springboot.examples.bookstore.dto.BookDto;
import cn.xdevops.springboot.examples.bookstore.entity.Book;
import cn.xdevops.springboot.examples.bookstore.exception.BookNotFoundException;
import cn.xdevops.springboot.examples.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookController(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book findBook(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping
    public Book addBook(@Valid @RequestBody BookDto bookDto) {
        return bookRepository.save(modelMapper.map(bookDto, Book.class));
    }
}
