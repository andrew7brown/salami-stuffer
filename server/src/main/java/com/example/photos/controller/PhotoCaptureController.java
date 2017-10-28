package com.example.photos.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.photos.entities.PhotoCapture;
import com.example.photos.repo.PhotoCaptureRepository;

@RestController
public class PhotoCaptureController {

	@Autowired
	PhotoCaptureRepository photoRepo;
	
	@GetMapping(value = "/image")
	public ResponseEntity<byte[]> getImage3(@RequestParam String uuid) throws IOException {
		System.out.println("grabbing image");
		PhotoCapture photo = photoRepo.findPhotoCaptureByUuid(uuid);
		photo.getSaveUri();
		InputStream in = new FileInputStream(photo.getSaveUri());
		byte[] bytes = StreamUtils.copyToByteArray(in);
		return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
	}
}