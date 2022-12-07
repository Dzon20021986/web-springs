package com.geekbrains.tests;

import com.geekbrains.spring.web.cart.dto.Cart;
import com.geekbrains.spring.web.cart.dto.OrderDto;
import com.geekbrains.spring.web.cart.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = CartService.class)
public class DeleteToCartTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private KafkaTemplate<Long, OrderDto> kafkaTemplate;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private CacheManager cacheManager;

    @BeforeEach
    public void initCart(){
        cartService = new CartService(cacheManager, restTemplate, kafkaTemplate);
        Mockito.when(cacheManager.getCache("Cart")
                .get(Mockito.anyString(), Cart.class)).thenReturn(new Cart());
        cartService.clear("test_cart");
    }
}
