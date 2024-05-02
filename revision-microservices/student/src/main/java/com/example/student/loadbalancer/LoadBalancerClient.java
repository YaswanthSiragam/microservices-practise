package com.example.student.loadbalancer;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

@org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient(value = "address-service")
public class LoadBalancerClient {

    @Bean
    @LoadBalanced
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

}
