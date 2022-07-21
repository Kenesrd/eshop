package com.example.eshop.dto;

import com.example.eshop.domain.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderIntegrationDto {
    private Long id;
    private String username;
    private String address;
    private List<OrderDetailsDto> details;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderDetailsDto{
        private String product;
        private Double price;
        private Double amount;
        private Double sum;

        public OrderDetailsDto(OrderDetails details) {
            this.product = details.getProduct().getTitle();
            this.price = details.getPrice().doubleValue();
            this.amount = details.getAmount().doubleValue();
            this.sum = details.getPrice().multiply(details.getAmount()).doubleValue();
        }
    }
}
