package com.shoe_ecommerce.brand;

import com.shoe_ecommerce.brand.shared.domain.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
		value = { "com.shoe_ecommerce.brand" }
)
public class BrandApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrandApplication.class, args);
	}

}
