package com.example.profiler;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.controller.CameraController;
import com.example.repo.CameraRepository;

public class ImageProfiler {

	@Autowired
	CameraRepository cameraRepo;
	
	@Autowired 
	CameraController cameraController;
	
	public ImageProfiler(){
		cameraController.getCameraUrls().parallelStream().forEach(url -> this.profileImage(url));
	}
	
	public void profileImage(String url){
		// Cosmic python stuff
	}
}
