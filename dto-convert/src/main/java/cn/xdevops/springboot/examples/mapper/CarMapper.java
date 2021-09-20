package cn.xdevops.springboot.examples.mapper;

import cn.xdevops.springboot.examples.domain.Car;
import cn.xdevops.springboot.examples.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);

    @Mapping(source = "seatCount", target = "numberOfSeats")
    Car carDtoToCar(CarDto carDto);

}
