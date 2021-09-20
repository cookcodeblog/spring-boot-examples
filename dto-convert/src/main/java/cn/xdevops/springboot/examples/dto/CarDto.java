package cn.xdevops.springboot.examples.dto;

import cn.xdevops.springboot.examples.domain.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private String make;
    private int seatCount;
    private CarType type;
}
