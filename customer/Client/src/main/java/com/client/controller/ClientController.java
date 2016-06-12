package com.client.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.client.obj.FromCompany;
import com.client.obj.ToCompany;

@RestController
@RequestMapping("communication")
public class ClientController {
	Random random = new Random();
	String url = "http://localhost:8091/communication/reply";

	@RequestMapping("/receive")
	public ToCompany receive(FromCompany fromCompany) {
		System.out.println("receive : " + fromCompany);
		RestTemplate restTemplate = new RestTemplate();
		ToCompany toCompany = new ToCompany(fromCompany.getBid_id(), random.nextInt(100000));
		System.out.println("receive : " + toCompany.getBid_id() + "\n" + "price : " + toCompany.getPrice());
		ToCompany receiveFromCompany = restTemplate.postForObject(url, toCompany, ToCompany.class);
		return receiveFromCompany;
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public ToCompany reply(@RequestBody ToCompany toCompany) {
		System.out.println("reply : " + toCompany);
		return toCompany;
	}
}