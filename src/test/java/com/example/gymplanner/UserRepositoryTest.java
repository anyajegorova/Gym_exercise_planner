package com.example.gymplanner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.gymplanner.domain.User;
import com.example.gymplanner.domain.UserRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository repository;
	
	@Test
	public void findByUsernameShouldReturnUser() {
		User users = repository.findByUsername("user");
		assertThat(users.getPasswordHash()).isNotNull();		
		
	}
}
