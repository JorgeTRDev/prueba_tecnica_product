package com.mycompany.prueba_tecnica_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PruebaTecnicaProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaProductApplication.class, args);
	}

}
