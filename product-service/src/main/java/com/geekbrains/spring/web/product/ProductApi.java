package com.geekbrains.spring.web.product;

import com.geekbrains.spring.web.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "Products", url = "http://localhost:8191/web-market-product/api/v1/products")
public interface ProductApi {


    @RequestMapping(method = RequestMethod.GET, value = "")
    List<ProductDto> getAllProducts();

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    ProductDto getProductById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.POST, value = "")
    ProductDto saveNewProduct(@RequestBody ProductDto productDto);


    @RequestMapping(method = RequestMethod.PUT, value = "")
    ProductDto updateProduct(@RequestBody ProductDto productDto);

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public  void deleteById(@PathVariable Long id);


}
