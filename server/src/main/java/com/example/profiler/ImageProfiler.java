package com.example.profiler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controller.CameraController;
import com.example.entities.Camera;
import com.example.entities.PhotoCapture;
import com.example.repo.CameraRepository;

@Service
public class ImageProfiler {

	@Autowired 
	CameraRepository cameraRepo;

	@Autowired 
	CameraController cameraController;

	public ImageProfiler() {
	}
	
	public void run(){
		System.out.println(this.cameraRepo.findAll());
		
		StreamSupport.stream(cameraRepo.findAll().spliterator(), true)
		.forEach(cam -> this.profileImage(this.fetchImage(cam)));
	}

	public Camera getCurrentCamera(String url) {
		Camera camera = new Camera();
		try {
			camera = this.cameraRepo.findOneCameraByUrl(url);
		} catch (Exception e) {
			camera.setUrl(url);
			cameraRepo.save(camera);
		}
		return camera;
	}

	public PhotoCapture fetchImage(Camera camera) {
		
		String saveUri = "./src/main/resources/images/" + camera.getUrl().substring(7, 19) + ".jpg";
		PhotoCapture capture = new PhotoCapture();
		System.out.println("save url: " + saveUri);
		try {
			URL url = new URL(camera.getUrl());
			InputStream is = url.openStream();
			OutputStream os;
			os = new FileOutputStream(saveUri);
			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			System.out.println("image saved");
			is.close();
			os.close();
			
			capture.setSaveUri(saveUri);
			List<PhotoCapture> photos = camera.getPhotos();
			photos.add(capture);
			camera.setPhotos(photos);
			cameraRepo.save(camera);
			
			System.out.println("PhotoCapture added to Camera");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return capture;
	}


	public void profileImage(PhotoCapture capture) {
		// Cosmic python stuff
		System.out.println("in python for: " + capture.getSaveUri());
	}
}
