package com.example.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Camera;
import com.example.entities.PhotoCapture;
import com.example.entities.Result;
import com.example.profiler.ImageProfiler;
import com.example.repo.CameraRepository;

@RestController
public class CameraController {

	@Autowired
	CameraRepository cameraRepo;
	
	@Autowired
	ImageProfiler profiler;

	@RequestMapping("/addCameras")
	public String addCameras() {

//		Result result = new Result("cat", 0.245);
//
//		PhotoCapture photo = new PhotoCapture("/some/file", LocalDate.now(), Arrays.asList(result));
		 
		Camera cam1 = new Camera("39.783317", "104.989556", "I-25 & 44th Ave", "http://i.cotrip.org/dimages/camera?imageURL=remote/CTMCCAM025N213-50-N.jpg", true);
		Camera cam2 = new Camera("123", "123", "Susan", "http://susandennis.noip.me/jpg/image.jpg", true);
		this.cameraRepo.save(cam1);
		this.cameraRepo.save(cam2);

		return "loaded cameras";
	}

	@RequestMapping("/getCameraUrls")
	public @ResponseBody List<String> getCameraUrls() {
		Iterable<Camera> cams = cameraRepo.findAll();
		return StreamSupport.stream(cams.spliterator(), true)
			.map(cam -> cam.getUrl())
			.collect(Collectors.toList());
	}
	
	@RequestMapping("/test")
	public void test() {
		profiler.run();
	}
}
