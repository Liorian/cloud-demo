package org.example.order.feign;

import org.example.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "service-product") // 启用Feign 这是Feign客户端
public interface ProductFeignClient {

    // MVC注解的两套使用逻辑
    //  1. 默认情况下，Spring MVC会根据请求的URL、请求方法、请求参数等，自动生成对应的请求路径，并调用对应的服务方法。
    //  2. 如果标注在FeignClient 是发送请求，则需要手动指定请求路径，否则会报错。
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
