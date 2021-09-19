package cn.xdevops.springboot.examples.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    @NotBlank(message = "Book name is mandatory")
    private String bookName;
    @NotBlank(message = "Author is mandatory")
    private String author;
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;
}
