package cn.xdevops.springboot.examples.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String customerFirstName;
    private String customerLastName;
    private String shippingStreet;
    private String shippingCity;
}
