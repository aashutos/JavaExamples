package com.interview.yoti.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Entry point for the Application.
 *
 */
@SpringBootApplication
@Import({MainConfiguration.class, DBConfiguration.class, SecurityConfiguration.class})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
