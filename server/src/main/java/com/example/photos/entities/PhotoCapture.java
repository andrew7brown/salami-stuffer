package com.example.photos.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.camera.entities.Camera;

@Entity
public class PhotoCapture {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	String uuid;
	
	String saveUri;

	LocalTime timestamp;
	
	LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "camera_id")
	Camera camera;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Result> results;

	public PhotoCapture() {
		this.uuid = UUID.randomUUID().toString();
	};
	
	public PhotoCapture(String saveUri, LocalTime timestamp, LocalDate date) {
		this.uuid = UUID.randomUUID().toString();
		this.saveUri = saveUri;
		this.timestamp = timestamp;
		this.date = date;
		this.results = new ArrayList<Result>();
	}
	
	public PhotoCapture(String saveUri, LocalTime timestamp, LocalDate date, List<Result> results) {
		this.uuid = UUID.randomUUID().toString();
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}