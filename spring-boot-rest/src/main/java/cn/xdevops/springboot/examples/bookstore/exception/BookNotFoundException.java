package cn.xdevops.springboot.examples.bookstore.exception;

public class BookNotFoundException extends ResourceNotFoundException {
    public BookNotFoundException(Long id) {
        super(id, "book");
    }
}
