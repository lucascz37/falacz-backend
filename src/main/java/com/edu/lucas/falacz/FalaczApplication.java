package com.edu.lucas.falacz;

import com.edu.lucas.falacz.configuration.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class FalaczApplication {

	public static void main(String[] args) {
		SpringApplication.run(FalaczApplication.class, args);
	}

}
