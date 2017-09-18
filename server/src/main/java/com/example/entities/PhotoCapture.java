package com.example.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PhotoCapture {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	String saveUri;
	
	@JsonIgnore
	LocalDate timestamp;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Result> results;

	public PhotoCapture(){};
	
	public PhotoCapture(String saveUri, LocalDate timestamp, List<Result> results) {
		this.saveUri = saveUri;
		this.timestamp = timestamp;
		this.results = results;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSaveUri() {
		return saveUri;
	}

	public void setSaveUri(String saveUri) {
		this.saveUri = saveUri;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
}
