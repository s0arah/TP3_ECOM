package com.emsi.inventoryservice;

import com.emsi.inventoryservice.entities.Product;
import com.emsi.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository , RepositoryRestConfiguration repositoryRestConfiguration) {
		return args -> {

			repositoryRestConfiguration.exposeIdsFor(Product.class);

			productRepository.saveAll(

					List.of(

							Product.builder()
									.name("Computer")
									.quantity(12)
									.price(1200)
									.build(),
							Product.builder()
									.name("printer")
									.quantity(32)
									.price(545)
									.build(),
							Product.builder()
									.name("smartphone")
									.quantity(55)
									.price(15500)
									.build()
					)


			);

		};
	}

}
