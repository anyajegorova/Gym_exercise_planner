package com.example.gymplanner;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.gymplanner.domain.Type;
import com.example.gymplanner.domain.TypeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TypeRepositoryTest {
	@Autowired
	private TypeRepository trepository;

	@Test
	public void findByNameReturnsType() {
		List<Type> types = trepository.findByName("Warmup");
		assertThat(types.get(0).getTypeId()).isEqualTo(156);
	}

}
