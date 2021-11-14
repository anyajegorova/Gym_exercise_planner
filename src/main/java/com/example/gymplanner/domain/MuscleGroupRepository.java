package com.example.gymplanner.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MuscleGroupRepository extends CrudRepository<MuscleGroup, Long> {
	List<MuscleGroup> findByName(String name);
}
