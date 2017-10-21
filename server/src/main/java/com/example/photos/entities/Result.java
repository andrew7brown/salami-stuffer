package com.example.photos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Result {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	String tags;
	
	Double weight;
	
	public Result(){};
	
	public Result(String tags, Double weight) {
		this.tags = tags;
		this.weight = weight;
	}

	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
