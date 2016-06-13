package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.repository.UsersRepository;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class SchedulerApplication implements CommandLineRunner{

	@Autowired
	UsersRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
