package com.shoe_ecommerce.brand;

import org.springframework.boot.SpringApplication;

public class TestBrandApplication {

	public static void main(String[] args) {
		SpringApplication.from(BrandApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
