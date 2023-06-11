package com.ecommerce.products.publishers;

import com.ecommerce.products.dtos.OrderDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderProductReply {

    @Value(value = "${ecommerce.broker.exchange.orderProductCommand}")
    private String orderProductExchange;
    @Value(value = "${ecommerce.broker.key.bindOrderProductReplyCommand}")
    private String bindOrderProduct;
    private final RabbitTemplate rabbitTemplate;

    public OrderProductReply(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(OrderDto orderDto) {
        rabbitTemplate.convertAndSend(orderProductExchange, bindOrderProduct, orderDto);
    }
}
