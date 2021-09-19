package cn.xdevops.springboot.examples.bookstore.controller;

import cn.xdevops.springboot.examples.bookstore.dto.BookDto;
import cn.xdevops.springboot.examples.bookstore.entity.Book;
import cn.xdevops.springboot.examples.bookstore.exception.BookNotFoundException;
import cn.xdevops.springboot.examples.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto findBook(@PathVariable Long id) {
        return modelMapper.map(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)), BookDto.class);
    }

    @PostMapping
    public BookDto addBook(@Valid @RequestBody BookDto bookDto) {
        return modelMapper.map(bookRepository.save(modelMapper.map(bookDto, Book.class)), BookDto.class);
    }

    @PutMapping("/{id}")
    public BookDto saveOrUpdate(@Valid @RequestBody BookDto bookDto, @PathVariable Long id) {
        return modelMapper.map(bookRepository.findById(id)
                .map(book -> {
                    book.setBookName(bookDto.getBookName());
                    book.setAuthor(bookDto.getAuthor());
                    book.setPrice(bookDto.getPrice());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    Book book = modelMapper.map(bookDto, Book.class);
                    book.setId(id);
                    return bookRepository.save(book);
                }), BookDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
