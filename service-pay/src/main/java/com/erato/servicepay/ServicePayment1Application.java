package com.erato.servicepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableScheduling
@EnableFeignClients
public class ServicePayment1Application {

    public static void main(String[] args) {
        SpringApplication.run(ServicePayment1Application.class, args);
    }

}
