package com.example.reactivewebaap;

import com.example.reactivewebaap.domain.Customer;
import com.example.reactivewebaap.repo.CustomerRepo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import reactor.core.publisher.Flux;

import java.util.UUID;

@SpringBootApplication
@EnableReactiveCassandraRepositories
public class ReactiveWebAapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebAapApplication.class, args);

	}

	@Bean
	ApplicationRunner applicationRunner(CustomerRepo customerRepo) {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				customerRepo
						.deleteAll()
						.thenMany(Flux.just("Amarendra,Kumar,1000","Prateek,Urmaliya,1500","Deepesh,Yadav,2000").map( str -> str.split(",")).map(strArray ->
								new Customer(UUID.randomUUID().toString(),strArray[0], strArray[1], new Double(strArray[2]))). flatMap(customer -> customerRepo.save(customer)))
						.thenMany(customerRepo.findAll())
						.subscribe(System.out::println);
			}
		};
	}
}
