package com.example.reactivewebaap.api;

import com.example.reactivewebaap.domain.Customer;
import com.example.reactivewebaap.repo.CustomerRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CustomerResource {

    @Bean
    RouterFunction<?> router(CustomerRepo customerRepo) {
        return route(GET("/customer"), r-> ServerResponse.ok().body(customerRepo.findAll(), Customer.class))
                .and(route(GET("/customer/{id}"), r -> ServerResponse.ok().body(customerRepo.findById(r.pathVariable("id")), Customer.class)))
                .and(route(POST("/customer/"), r -> r.bodyToMono(Customer.class).flatMap( customer -> customerRepo.save(customer)).flatMap(customer2 ->
                        ServerResponse.created(URI.create("/customer/" + customer2.getKey())).build())))
                .and(route(PUT("/customer/{id}"), r -> Mono.zip((data) -> {
                            Customer customer = (Customer) data[0];
                            Customer customer1 = (Customer) data[1];
                            customer.setLastName(customer1.getLastName());
                            customer.setFirstName(customer1.getFirstName());
                            customer.setSalary(customer1.getSalary());
                            return customer;
                        },
                        customerRepo.findById(r.pathVariable("id")),
                        r.bodyToMono(Customer.class)
                )
                        .cast(Customer.class)
                        .flatMap(customer -> customerRepo.save(customer))
                        .flatMap(customer-> ServerResponse.noContent().build())))
                .and(route(DELETE("/customer/{id}"), r -> ServerResponse.noContent().build(customerRepo.deleteById(r.pathVariable("id")))));

    }


}
