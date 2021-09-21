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
    @NotNull(message = "Book name can not be null")
    @NotBlank(message = "Book name can not be blank")
    private String bookName;
    @NotNull(message = "Author can not be null")
    @NotBlank(message = "Author can not be blank")
    private String author;
    @NotNull(message = "Price can not be null")
    private BigDecimal price;
}
