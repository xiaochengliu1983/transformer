package com.example.transformer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transformer implements Comparable<Transformer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int Strength;
	
	private int Intelligence;
	
	private int Speed;
	
	private int Endurance;
	
	private int Rank;
	
	private int Courage;
	
	private int Firepower;
	
	private int Skill;
	
	private String type;
	
	private String name;

	public Transformer(){}
	
	public Transformer(String name, String type, int strength, int intelligence, int speed, int endurance, int rank, int courage, int firepower, int skill){
		this.name = name;
		this.type = type;
		this.Strength = strength;
		this.Intelligence = intelligence;
		this.Speed = speed;
		this.Endurance = endurance;
		this.Rank = rank;
		this.Courage = courage;
		this.Firepower = firepower;
		this.Skill = skill;
	}
	
	public int getStrength() {
		return Strength;
	}

	public void setStrength(int strength) {
		Strength = strength;
	}

	public int getIntelligence() {
		return Intelligence;
	}

	public void setIntelligence(int intelligence) {
		Intelligence = intelligence;
	}

	public int getSpeed() {
		return Speed;
	}

	public void setSpeed(int speed) {
		Speed = speed;
	}

	public int getEndurance() {
		return Endurance;
	}

	public void setEndurance(int endurance) {
		Endurance = endurance;
	}

	public int getRank() {
		return Rank;
	}

	public void setRank(int rank) {
		Rank = rank;
	}

	public int getCourage() {
		return Courage;
	}

	public void setCourage(int courage) {
		Courage = courage;
	}

	public int getFirepower() {
		return Firepower;
	}

	public void setFirepower(int firepower) {
		Firepower = firepower;
	}

	public int getSkill() {
		return Skill;
	}

	public void setSkill(int skill) {
		Skill = skill;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getOverallRating() {
		return this.Strength + this.Intelligence + this.Speed + this.Endurance + this.Firepower;
	}

	@Override
	public int compareTo(Transformer o) {
		int compareRank=((Transformer)o).getRank();
		return compareRank - this.Rank;
	}
	
}
