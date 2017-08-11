package com.buscapecompany.poc.cucumbermicroservice;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.buscapecompany.poc.cucumbermicroservice.model.Product;
import com.buscapecompany.poc.cucumbermicroservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class CucumberMicroserviceApplication {

	private static final Logger logger = LoggerFactory.getLogger(CucumberMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CucumberMicroserviceApplication.class, args);
	}

	public static ConfigurableApplicationContext run(String[] args) {
		return SpringApplication.run(CucumberMicroserviceApplication.class, args);
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
		return converter;
	}

	@Bean
	@Transactional
	public CommandLineRunner setup(ProductRepository productRepository) {
		return (args) -> {
			Arrays.asList(1, 2, 3, 4, 5, 6).stream().forEach(idx -> {

				final Product product = new Product("Product " + idx, "A Product " + idx, null);
				final Product parent = createProduct(productRepository, product);

				Arrays.asList(1, 2, 3, 4, 5, 6).stream().forEach(idxChild -> {
					createProduct(productRepository, new Product("Child Product " + idxChild,
							"A Child Product " + idxChild + " of Parent " + parent.getName(), parent));
				});
			});

			logger.info("The sample data has been generated");
			logger.debug("The sample data has been generated");
			logger.error("The sample data has been generated");
			logger.trace("The sample data has been generated");
			logger.warn("The sample data has been generated");
		};
	}

	@Transactional
	private Product createProduct(ProductRepository productRepository, Product p) {
		return productRepository.saveAndFlush(p);
	}
}
