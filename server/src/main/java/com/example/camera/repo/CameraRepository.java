package com.example.camera.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.camera.entities.Camera;
import com.example.camera.entities.InlineCamera;

@RepositoryRestResource(excerptProjection = InlineCamera.class)
public interface CameraRepository extends CrudRepository<Camera, Integer>{

	public Camera findOneCameraByUrl(String url);
	
}
