package br.com.clientes.bff.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(value = {"br.com.clientes.bff.mongo"})
public class MiddlewareSalesforceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddlewareSalesforceApplication.class, args);
	}

}
