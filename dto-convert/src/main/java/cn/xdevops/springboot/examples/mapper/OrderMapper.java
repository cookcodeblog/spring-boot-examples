package cn.xdevops.springboot.examples.mapper;

import cn.xdevops.springboot.examples.domain.Order;
import cn.xdevops.springboot.examples.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "order.customer.name.firstName", target = "customerFirstName"),
            @Mapping(source = "order.customer.name.lastName", target = "customerLastName"),
            @Mapping(source = "order.shippingAddress.street", target = "shippingStreet"),
            @Mapping(source = "order.shippingAddress.city", target = "shippingCity")
    })
    OrderDto toOrderDto(Order order);
}
