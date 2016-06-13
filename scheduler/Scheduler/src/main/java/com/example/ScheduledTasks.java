package com.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dao.UserUsages;
import com.example.repository.UserUsagesRepository;
import com.example.repository.UsersRepository;
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
	UserUsagesRepository userUsagesRepository;

	@Autowired
	UsersRepository usersRepository;

	// 매달 1일 00:00
	@Scheduled(cron = "0 0 0 1 * *")
	@Transactional
	public void testMail() {
		Multimap<String, UserUsages> multiMap = ArrayListMultimap.create();
		List<UserUsages> usages = null;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

		LocalDate current = LocalDate.now();
		LocalDate start = current.minus(1, ChronoUnit.MONTHS);
		start = start.withDayOfMonth(1);

		LocalDate end = current.minus(1, ChronoUnit.DAYS);
		end = end.withDayOfMonth(end.lengthOfMonth());
		LocalDate testEnd = current.plus(1, ChronoUnit.MONTHS);

		usages = userUsagesRepository.findByDatesBetween(start.format(dateTimeFormatter),
				end.format(dateTimeFormatter));

		// test
		// usages =
		// userUsagesRepository.findByDatesBetween(start.format(dateTimeFormatter),
		// testEnd.format(dateTimeFormatter));

		System.out.println(start);
		System.out.println(testEnd);
		System.out.println(usages);

		for (UserUsages userUsage : usages) {
			multiMap.put(userUsage.getUser().getEmail(), userUsage);
			System.out.println("email : " + userUsage.getUser().getEmail());
		}

		for (String toEmail : multiMap.keySet()) {
			List<UserUsages> userUsages = (List<UserUsages>) multiMap.get(toEmail);
			String msg = "";
			int total = 0;

			for (UserUsages userUsage : userUsages) {
				msg += userUsage.getUpdatedAt() + " " + userUsage.getKeyword() + " " + userUsage.getBid_price() + "\n";
				total += userUsage.getBid_price();
			}
			msg += "Total Fee : " + total;
			mailMail.sendMail("zino.kim0708@gmail.com", toEmail, "Your Total Fee", msg);
			System.out.println("send mail");
		}
		System.out.println("success");
	}
}