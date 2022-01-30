package com.rdx.factory.nebula;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rdx.factory"})
public class NebulaStockApp {
    public static void main(String[] args) {
        SpringApplication.run(NebulaStockApp.class, args);
    }
}
