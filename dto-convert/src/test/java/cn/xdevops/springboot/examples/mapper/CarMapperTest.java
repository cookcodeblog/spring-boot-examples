package cn.xdevops.springboot.examples.mapper;

import cn.xdevops.springboot.examples.domain.Car;
import cn.xdevops.springboot.examples.domain.CarType;
import cn.xdevops.springboot.examples.dto.CarDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarMapperTest {

    @Test
    @DisplayName("convert car to car dto")
    void convertCarToCarDto() {
        Car car = new Car("Young", 5, CarType.TYPE_A);
        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        assertThat(carDto)
                .extracting(CarDto::getMake, CarDto::getSeatCount, CarDto::getType)
                .containsExactly(car.getMake(), car.getNumberOfSeats(), car.getType());
    }

    @Test
    @DisplayName("convert car dto to car")
    void convertCarDtoToCar() {
        CarDto carDto = new CarDto("Sport", 4, CarType.TYPE_B);
        Car car = CarMapper.INSTANCE.carDtoToCar(carDto);

        assertThat(car)
                .extracting(Car::getMake, Car::getNumberOfSeats, Car::getType)
                .containsExactly(carDto.getMake(), carDto.getSeatCount(), carDto.getType());
    }
}