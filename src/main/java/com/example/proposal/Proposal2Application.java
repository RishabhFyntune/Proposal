package com.example.proposal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Proposal2Application {

	public static void main(String[] args) {
		SpringApplication.run(Proposal2Application.class, args);
	}


}
