package cn.xdevops.springboot.examples.dto;

import cn.xdevops.springboot.examples.domain.Address;
import cn.xdevops.springboot.examples.domain.Customer;
import cn.xdevops.springboot.examples.domain.Name;
import cn.xdevops.springboot.examples.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoConvertTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("convert order to order dto")
    void convertOrderToOrderDto() {
        Customer customer = new Customer(new Name("Xiaoming", "Zhang"));
        Address shippingAddress = new Address("happy street", "lucy city");
        Order order = new Order(customer, shippingAddress);
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);

        assertThat(orderDto)
                .extracting(OrderDto::getCustomerFirstName, OrderDto::getCustomerLastName,
                        OrderDto::getShippingStreet, OrderDto::getShippingCity)
                .containsExactly(order.getCustomer().getName().getFirstName(), order.getCustomer().getName().getLastName(),
                        order.getShippingAddress().getStreet(), order.getShippingAddress().getCity());
    }

}