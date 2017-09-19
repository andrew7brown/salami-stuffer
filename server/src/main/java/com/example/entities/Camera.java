package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Camera {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int id;

	String latitude;
	
	String longitude;
	
	String namedLocation;
	
	String url;
	
	Boolean active;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<PhotoCapture> photos;
	
	public Camera(){};
	
	public Camera(String latitude, String longitude, String namedLocation, String url, Boolean active) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.namedLocation = namedLocation;
		this.url = url;
		this.active = active;
		this.photos = new ArrayList<PhotoCapture>();
	}
	
	public Camera(String latitude, String longitude, String namedLocation, String url, Boolean active,
			List<PhotoCapture> photos) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.namedLocation = namedLocation;
		this.url = url;
		this.active = active;
		this.photos = photos;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNamedLocation() {
		return namedLocation;
	}

	public void setNamedLocation(String namedLocation) {
		this.namedLocation = namedLocation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<PhotoCapture> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoCapture> photos) {
		this.photos = photos;
	}
	
}
