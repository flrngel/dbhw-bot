package com.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.client.obj.ToCompany;
import com.client.repository.ToCompanyRepository;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner{
	
	@Autowired
	ToCompanyRepository toCompanyRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		toCompanyRepository.save(Arrays.asList(new ToCompany("nike shoes", 10000, "https://www.google.co.kr/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwiA-sa57qTNAhXIQpQKHX5pDYgQjRwIBw&url=http%3A%2F%2Fwww.footlocker.com%2FMens%2FNike%2FShoes%2F_-_%2FN-24ZzzZrj&psig=AFQjCNFR-C7bP_90By9vX6bcLxPRDxkAHQ&ust=1465902485704933", "http://www.auction.co.kr/", "8091", true),
				new ToCompany("adidas shoes", 10000, "https://www.google.co.kr/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwiA-sa57qTNAhXIQpQKHX5pDYgQjRwIBw&url=http%3A%2F%2Fwww.footlocker.com%2FMens%2FNike%2FShoes%2F_-_%2FN-24ZzzZrj&psig=AFQjCNFR-C7bP_90By9vX6bcLxPRDxkAHQ&ust=1465902485704933", "http://www.auction.co.kr/", "8091", true),
				new ToCompany("trash", 10000, "https://www.google.co.kr/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjv_pOR76TNAhVGk5QKHeRwDBoQjRwIBA&url=http%3A%2F%2Fwww.cdrental.co.kr%2Fproduct%2Flist.html%3Fcate_no%3D114&psig=AFQjCNGb_foYFFjenewSXWigFO3cYcQJLg&ust=1465902671253678", "http://www.auction.co.kr/", "8091", true)));
	}
}
