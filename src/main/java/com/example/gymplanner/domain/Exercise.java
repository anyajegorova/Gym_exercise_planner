package com.example.gymplanner.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String reps;
	private double weight;

	@ManyToOne
	@JoinColumn(name = "typeId")
	@JsonManagedReference
	private Type type;
	
	@ManyToOne
	@JoinColumn(name = "groupId")
	@JsonManagedReference
	private MuscleGroup muscleGroup;

	public Exercise() {

	}

	public Exercise(String name, String reps, double weight, Type type, MuscleGroup muscleGroup) {
		super();

		this.name = name;
		this.reps = reps;
		this.weight = weight;
		this.type = type;
		this.muscleGroup = muscleGroup;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReps() {
		return reps;
	}

	public void setReps(String reps) {
		this.reps = reps;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public MuscleGroup getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(MuscleGroup muscleGroup) {
		this.muscleGroup = muscleGroup;
	}

}
