package com.example.photos.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.photos.entities.PhotoCapture;
import com.example.photos.repo.PhotoCaptureRepository;

@RestController
public class PhotoCaptureController {

	@Autowired
	PhotoCaptureRepository photoRepo;

	@RequestMapping(value = "/sid", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@RequestParam("uuid") String uuid) throws IOException {
		PhotoCapture photo = photoRepo.findOnePhotoCaptureByUuid(uuid);
		ClassPathResource imgFile = new ClassPathResource(photo.getSaveUri());
		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);

	}
}
