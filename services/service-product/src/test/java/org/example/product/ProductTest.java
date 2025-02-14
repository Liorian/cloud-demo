package org.example.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;


@SpringBootTest
public class ProductTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    public void testNacosServiceDiscovery() throws Exception {
        List<String> services = nacosServiceDiscovery.getServices();
        for (String service : services) {
            System.out.println("service = " + service);
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("instance.getServiceId() = " + instance.getServiceId());
                System.out.println("instance.getHost() = " + instance.getHost());
            }
        }
    }

    @Test
    public void testDiscoveryClient() {
        for (String service : discoveryClient.getServices()) {
            System.out.println("service = " + service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("instance.getServiceId() = " + instance.getServiceId());
                System.out.println("instance.getHost() = " + instance.getHost());
                System.out.println("instance.getPort() = " + instance.getPort());
            }
        }

    }
}
