package com.example.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Camera;

public interface CameraRepository extends CrudRepository<Camera, Long>{

	public Camera findOneCameraByUrl(String url);
	
}
