package com.client.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.obj.FromCompany;
import com.client.obj.ToCompany;
import com.client.repository.ToCompanyRepository;

@RestController
@RequestMapping("communication")
public class ClientController {
	Random random = new Random();
	String url = "http://localhost:8091/communication/reply";

	@Autowired
	ToCompanyRepository toCompanyRepository;

	String token = "8091";
	
	@RequestMapping("/receive")
	public ToCompany receive(FromCompany fromCompany) {
		System.out.println("receive : " + fromCompany);

		ToCompany toCompany = toCompanyRepository.findOne(fromCompany.getKeyword());
		if (toCompany == null)
			toCompany.setBid(false);
		toCompany.setApiToken(token);
		
		return toCompany;
	}
}