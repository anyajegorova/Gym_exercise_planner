package com.example.gymplanner.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MuscleGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long groupId;

	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "muscleGroup")
	private List<Exercise> exercises;

	public MuscleGroup() {

	}

	public MuscleGroup(String name) {
		super();
		this.name = name;

	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

}
