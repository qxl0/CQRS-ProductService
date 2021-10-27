package com.qiang.ProductService.command.api.events;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.qiang.ProductService.command.api.data.Product;
import com.qiang.ProductService.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository productRespository;

    public ProductEventsHandler(ProductRepository productRespository) {
        this.productRespository = productRespository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        Product product =
                new Product();
        BeanUtils.copyProperties(event, product);
        productRespository.save(product);
    }
}
