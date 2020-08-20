package com.george.test.inter.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.george.test.inter.domain.User;
import com.george.test.inter.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void test01whenCreatedShouldPersistData() {
		User user = new User(null, "Test Repo", "repo@mail.com");
		this.repository.save(user);
		
		assertThat(user.getId()).isNotNull();
		assertThat(user.getName()).isEqualTo("Test Repo");
		assertThat(user.getEmail()).isEqualTo("repo@mail.com");
	}
	
	@Test
	public void test02whenDeleteShouldRemoveData() {
		User user = new User(null, "Test Repo", "repo@mail.com");
		this.repository.save(user);
		this.repository.delete(user);
		
		assertThat(this.repository.findById(user.getId())).isEmpty();
	}
	
	@Test
	public void test03whenUpdateShouldChangeAndPersistData() {
		User user = new User(null, "Test Repo", "repo@mail.com");
		this.repository.save(user);
		
		user = new User(null, "Test", "repo.test@mail.com");
		this.repository.save(user);
		
		user = this.repository.findById(user.getId()).orElse(null);
		
		assertThat(user.getName()).isEqualTo("Test");
		assertThat(user.getEmail()).isEqualTo("repo.test@mail.com");
	}
	
	@Test
	public void test04whenListingShouldReturnAllData() {
		List<User> users = this.repository.findAll();
		
		assertThat(users.size()).isEqualTo(2);
	}
}
