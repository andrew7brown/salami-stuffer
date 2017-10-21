package com.example.photos.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.photos.entities.PhotoCapture;

public interface PhotoCaptureRepository extends CrudRepository<PhotoCapture, Long>{

	public PhotoCapture findOnePhotoCaptureByUuid(String uuid);
	
}
