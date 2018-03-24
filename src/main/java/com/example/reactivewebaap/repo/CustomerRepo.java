package com.example.reactivewebaap.repo;

import com.example.reactivewebaap.domain.Customer;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepo extends ReactiveCassandraRepository<Customer, String>{

    Flux<Customer> findByLastName(final String lastName);
    Flux<Customer> findByFirstName(final String firstName);
}
