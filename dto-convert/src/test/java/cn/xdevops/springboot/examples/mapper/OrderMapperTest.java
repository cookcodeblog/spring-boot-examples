package cn.xdevops.springboot.examples.mapper;

import cn.xdevops.springboot.examples.domain.Address;
import cn.xdevops.springboot.examples.domain.Customer;
import cn.xdevops.springboot.examples.domain.Name;
import cn.xdevops.springboot.examples.domain.Order;
import cn.xdevops.springboot.examples.dto.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderMapperTest {

    @Test
    @DisplayName("convert multiple objects to order dto")
    void convertMultipleObjectsToOrderDto() {
        Customer customer = new Customer(new Name("Xiaoming", "Zhang"));
        Address shippingAddress = new Address("happy street", "lucy city");
        Order order = new Order(customer, shippingAddress);

        OrderDto orderDto = OrderMapper.INSTANCE.toOrderDto(order);
        System.out.println(orderDto);
        assertThat(orderDto)
                .extracting(OrderDto::getCustomerFirstName, OrderDto::getCustomerLastName,
                        OrderDto::getShippingStreet, OrderDto::getShippingCity)
                .containsExactly(order.getCustomer().getName().getFirstName(), order.getCustomer().getName().getLastName(),
                        order.getShippingAddress().getStreet(), order.getShippingAddress().getCity());
    }
}