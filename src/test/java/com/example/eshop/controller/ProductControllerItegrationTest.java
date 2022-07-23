package com.example.eshop.controller;

import com.example.eshop.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerItegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void checkGetProductById(){
        ResponseEntity<ProductDto> entity = restTemplate.getForEntity("/products/" + 1, ProductDto.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}
