package com.ecommerce.products.consumers;

import com.ecommerce.products.dtos.OrderDto;
import com.ecommerce.products.enums.OrderStatus;
import com.ecommerce.products.enums.ProductExceptionMessage;
import com.ecommerce.products.exceptions.ProductException;
import com.ecommerce.products.publishers.OrderProductReply;
import com.ecommerce.products.services.interfaces.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderProductRequest {
    private final OrderProductReply orderProductReply;
    private final ProductService productService;

    public OrderProductRequest(OrderProductReply orderProductReply, ProductService productService) {
        this.orderProductReply = orderProductReply;
        this.productService = productService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ecommerce.broker.queue.orderProductRequest}", durable = "true"),
            exchange = @Exchange(value = "${ecommerce.broker.exchange.orderProductCommand}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${ecommerce.broker.key.bindOrderProductRequestCommand}"
    ))
    private void listenOrderProductCommand(@Payload OrderDto orderDto) {
        System.out.println(orderDto);
        try {
            productService.subtractProduct(orderDto.getOrderItems());
        } catch (ProductException e) {
            orderDto.setOrderStatus(OrderStatus.CANCELED.toString());
            orderDto.setProductsInStock(false);
            orderDto.setMessage(e.getMsg());
            orderProductReply.publish(orderDto);
            e.printStackTrace();
            log.error(e.getMsg());
            return;
        }
        orderDto.setProductsInStock(true);
        orderProductReply.publish(orderDto);
    }
}
