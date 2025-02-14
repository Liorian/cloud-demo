package org.example.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.order.bean.Order;
import org.example.order.service.OrderService;
import org.example.product.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Order createOder(Long productId, Long userId) {
        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);
        Order order = new Order();
        order.setId(1L);
        order.setUserId(userId);
        order.setProductList(Arrays.asList(product));
        order.setAddress("北京");
        order.setNickName("张三");
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        return order;
    }

    /**
     * 使用负载均衡注解
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        log.info("url:{}", url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    /**
     * 使用负载均衡
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = choose.getUri().toString() + "/product/" + productId;
        log.info("url:{}", url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    /**
     * 不使用负载均衡
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String url = serviceInstance.getUri().toString() + "/product/" + productId;
        log.info("url:{}", url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
