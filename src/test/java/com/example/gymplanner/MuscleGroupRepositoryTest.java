package com.example.gymplanner;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

import com.example.gymplanner.domain.MuscleGroup;
import com.example.gymplanner.domain.MuscleGroupRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MuscleGroupRepositoryTest {
	@Autowired
	private MuscleGroupRepository mrepository;
	
	@Test
	public void findByNameReturnsMuscleGroup() {
		List<MuscleGroup> muscleGroups = mrepository.findByName("Back");
		assertThat(muscleGroups.get(0).getGroupId()).isEqualTo(161);
		
	}

}
