package com.example.productservice.comsumer;

import com.example.productservice.dto.OrderItemDto;
import com.example.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateConsumer {

    private final JsonMapper jsonMapper;

    private final ProductService productService;

    @KafkaListener(topics = "order-created")
    public void listen(String orderString) {
        try {
            JsonNode root = jsonMapper.readTree(orderString);
            JsonNode orderItemsNode = root.get("orderItems");

            List<OrderItemDto> orderItems = jsonMapper.convertValue(
                    orderItemsNode,
                    jsonMapper.getTypeFactory()
                            .constructCollectionType(List.class, OrderItemDto.class)
            );

            productService.updateQuantity(orderItems);
            log.info("Received order: {}", orderString);
        }catch (Exception e){
            log.error("Received error: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}