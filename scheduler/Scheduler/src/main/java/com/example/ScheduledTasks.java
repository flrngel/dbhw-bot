package com.example;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dao.UserUsage;
import com.example.repository.UserRepository;
import com.example.repository.UserUsageRepository;
import com.example.service.MailMail;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

@Component
@EnableAsync
@Transactional
public class ScheduledTasks {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	MailMail mailMail;

	@Autowired
	UserUsageRepository userUsageRepository;

	@Autowired
	UserRepository userRepository;

	// @Scheduled(cron = "0 */1 * * * *")
	@Transactional
	public void reportCurrentTime() throws InterruptedException {
		System.out.println("The time is now " + dateFormat.format(new Date()));
		System.out.println(userRepository.findAllU().get(0).getUsername());
	}

	// @Scheduled(cron = "* 37 * * * *")
	// public void reportMail() {
	// System.out.println("The time is now " + dateFormat.format(new Date()));
	// System.out.println(userRepository.findAll());
	// }

	// 10 분마
	@Scheduled(cron = "0 0 0 1 * *")
	@Transactional
	public void testMail() {
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