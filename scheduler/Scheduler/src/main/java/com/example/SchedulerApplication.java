package com.example;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.repository.UserRepository;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class SchedulerApplication{

	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		userRepository.findOne("zino.kim0708@gmail.com");
//	}
}
