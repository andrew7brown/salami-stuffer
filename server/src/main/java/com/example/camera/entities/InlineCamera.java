package com.example.camera.entities;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.example.photos.entities.PhotoCapture;

@Projection(name = "inlineCamera", types = { Camera.class })
public interface InlineCamera {

	int getId();
	Double getLatitude();
	Double getLongitude();
	String getNamedLocation();
	String getUrl();
	Boolean getActive();
	List<PhotoCapture> getPhotos();
	
}
