package com.george.test.inter;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.george.test.inter.domain.SingleDigit;
import com.george.test.inter.domain.User;
import com.george.test.inter.repositories.SingleDigitRepository;
import com.george.test.inter.repositories.UserRepository;

@SpringBootApplication
public class TestInterBankApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SingleDigitRepository singleDigitRepository;

	public static void main(String[] args) {
		SpringApplication.run(TestInterBankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "George", "george@mail.com");
		
		User user2 = new User(null, "Tester", "tester@mail.com");

		SingleDigit sd = new SingleDigit(null, 123, 1, user1);
		
		sd.sumDigit();
		
		user1.getSingleDigits().addAll(Arrays.asList(sd));

		userRepository.saveAll(Arrays.asList(user1 , user2));
		singleDigitRepository.save(sd);
	}

}
