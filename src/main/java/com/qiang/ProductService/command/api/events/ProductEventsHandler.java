package com.qiang.ProductService.command.api.events;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.qiang.ProductService.command.api.data.Product;
import com.qiang.ProductService.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    private ProductRepository productRespository;

    public ProductEventsHandler(ProductRepository productRespository) {
        this.productRespository = productRespository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product =
                new Product();
        BeanUtils.copyProperties(event, product);
        productRespository.save(product);
        throw new Exception("Exception occurred");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
