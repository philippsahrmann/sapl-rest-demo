package com.sapldemo.restdemo;

import java.io.IOException;

import org.openconjurer.authz.api.interpreter.PolicyEvaluationException;
import org.openconjurer.authz.pdp.embedded.CombiningAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	
	@Bean
	public PDPFacade pdpFacade() throws IOException, PolicyEvaluationException {
		return new PDPFacade("file:D:/sapl", CombiningAlgorithm.DENY_OVERRIDES);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
