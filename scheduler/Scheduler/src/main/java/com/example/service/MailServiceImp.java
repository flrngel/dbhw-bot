package com.example.service;

import com.example.repository.UserUsageRepository;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.example.dao.UserUsage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImp {

	@Autowired
	UserUsageRepository userUsageRepository;
	
	@Autowired
	MailMail mailMail;

	public void sendMail() {
		Multimap<String, UserUsage> multiMap = ArrayListMultimap.create();
		List<UserUsage> usages = null;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

		LocalDateTime current = LocalDateTime.now();
		LocalDateTime start = current.minus(1, ChronoUnit.MONTHS);
		LocalDateTime end = current.minus(1, ChronoUnit.DAYS);
		LocalDateTime testEnd = current.plus(1, ChronoUnit.MONTHS);

		// usages =
		// userUsageRepository.findByDatesBetween(start.format(dateTimeFormatter),
		// end.format(dateTimeFormatter));

		// test
		usages = userUsageRepository.findByDatesBetween(start.format(dateTimeFormatter),
				testEnd.format(dateTimeFormatter));

		for (UserUsage userUsage : usages) {
			multiMap.put(userUsage.getUser().getUsername(), userUsage);
		}

		for (String toEmail : multiMap.keySet()) {
			List<UserUsage> userUsages = (List<UserUsage>) multiMap.get(toEmail);
			String msg = "";
			int total = 0;

			for (UserUsage userUsage : userUsages) {
				msg += userUsage.getDate() + " " + userUsage.getWord() + " " + userUsage.getPrice() + "\n";
				total += userUsage.getPrice();
			}
			msg += "Total Fee : " + total;
			mailMail.sendMail("zino.kim0708@gmail.com", toEmail, "Your Total Fee", msg);
		}
	}
}
