package com.george.test.inter.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.george.test.inter.domain.SingleDigit;
import com.george.test.inter.repositories.SingleDigitRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SingleDigitRepositoryTest {

	@Autowired
	private SingleDigitRepository repository;
	
	@Test
	public void test01whenCreatedShouldPersistData() {
		SingleDigit singleDigit = new SingleDigit(null, 123, 1, null);
		singleDigit.sumDigit();
		this.repository.save(singleDigit);
		
		assertThat(singleDigit.getId()).isNotNull();
		assertThat(singleDigit.getDigit()).isEqualTo(123);
		assertThat(singleDigit.getTimes()).isEqualTo(1);
		assertThat(singleDigit.getResult()).isEqualTo(6);
	}
}
