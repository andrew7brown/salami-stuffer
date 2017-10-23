package com.example.photos.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.photos.entities.PhotoCapture;
import com.example.photos.repo.PhotoCaptureRepository;

@RestController
public class PhotoCaptureController {

	@Autowired
	PhotoCaptureRepository photoRepo;

	@RequestMapping(value = "/sid", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@RequestParam("uuid") String uuid) throws IOException {
		PhotoCapture photo = photoRepo.findPhotoCaptureByUuid(uuid);
		System.out.println(photo.getUuid());
		ClassPathResource imgFile = new ClassPathResource(photo.getSaveUri());
		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);

	}

	@GetMapping(value = "/getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@RequestParam("uuid") String uuid) throws IOException {
		PhotoCapture photo = photoRepo.findPhotoCaptureByUuid(uuid);
		System.out.println(photo.getUuid());
		System.out.println(photo.getSaveUri().substring(1));
		InputStream in = getClass().getResourceAsStream("/Users/eginter/git/salami-stuffer/server" + photo.getSaveUri().substring(1));
		return IOUtils.toByteArray(in);
	}
}
