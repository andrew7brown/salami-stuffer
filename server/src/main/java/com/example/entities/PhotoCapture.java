package com.example.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PhotoCapture {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	String saveUri;

	LocalTime timestamp;
	
	LocalDate date;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Result> results;

	public PhotoCapture(){};
	
	public PhotoCapture(String saveUri, LocalTime timestamp, LocalDate date) {
		this.saveUri = saveUri;
		this.timestamp = timestamp;
		this.date = date;
		this.results = new ArrayList<Result>();
	}
	
	public PhotoCapture(String saveUri, LocalTime timestamp, LocalDate date, List<Result> results) {
		this.saveUri = saveUri;
		this.timestamp = timestamp;
		this.date = date;
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

	public LocalTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}