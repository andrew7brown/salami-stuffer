package com.example.profiler;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.example.controller.CameraController;
import com.example.entities.Camera;
import com.example.entities.PhotoCapture;
import com.example.entities.Result;
import com.example.repo.CameraRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImageProfiler {

	@Autowired
	CameraRepository cameraRepo;

	@Autowired
	CameraController cameraController;

	public ImageProfiler() {
	}

	public void run() {
		StreamSupport.stream(cameraRepo.findAll().spliterator(), true)
				.forEach(cam -> {
					try {
						System.out.println("processing cam " + cam.getNamedLocation());
						this.profileImage(cam, this.fetchImage(cam));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
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

		String saveUri = "./src/main/resources/images/" + camera.getUrl().substring(7, 19) + LocalDate.now() + ".jpg";
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
			capture.setTimestamp(LocalTime.now());
			capture.setDate(LocalDate.now());
//			List<PhotoCapture> photos = camera.getPhotos();
//			photos.add(capture);
//			camera.setPhotos(photos);
//			cameraRepo.save(camera);

			System.out.println("PhotoCapture added to Camera");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return capture;
	}

	public void profileImage(Camera cam, PhotoCapture capture) throws Exception {
		
		Process p = Runtime.getRuntime()
				.exec("/Library/Frameworks/Python.framework/Versions/3.6/bin/python3 ImageClassifier.py " + capture.getSaveUri() + " 5");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String s = "";
		String jsonString = "";
		// read the output from the command
		System.out.println("##### Here is the standard output of the command:\n");
		while ((s = stdInput.readLine()) != null) {
			jsonString += s;
			System.out.println(s);
		}

		// read any errors from the attempted command
		System.out.println("##### Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}
		//jsonString = "[{'tags': 'solar dish, solar collector, solar furnace', 'weight': 0.81778657}, {'tags': 'bannister, banister, balustrade, balusters, handrail', 'weight': 0.027153652}, {'tags': 'bullet train, bullet', 'weight': 0.02078932}, {'tags': 'streetcar, tram, tramcar, trolley, trolley car', 'weight': 0.01631031}]";
		jsonString = jsonString.replaceAll("\"", "");
		System.out.println("json: " + jsonString);
		
		int returnVal = p.waitFor();
	
		//jsonString = "{ \"tags\": \"cinema, movie theater, movie theatre, movie house, picture palace\", \"weight\": 0.25862458}";
		//jsonString = "[{‘tags’: ‘studio couch, day bed’, ‘weight’: 0.66928029}, {‘tags’: ‘patio, terrace’, ‘weight’: 0.11289313}, {‘tags’: ‘sliding door’, ‘weight’: 0.082315378}, {‘tags’: ‘window shade’, ‘weight’: 0.050180443}, {‘tags’: ‘table lamp’, ‘weight’: 0.011996713}]";
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		List<Result> results = objectMapper.readValue(jsonString, new TypeReference<List<Result>>() {
		});

		System.out.println("size: " + results.size());
		
		for (Result result : results) {
			System.out.println(result.getTags() + " " + result.getWeight());
		}
		List<PhotoCapture> photos = cam.getPhotos();
		
		capture.setResults(results);
		photos.add(capture);
		cam.setPhotos(photos);
		System.out.println("saving photos to " + cam.getNamedLocation());
		try {
			cameraRepo.save(cam);
		} catch (TransactionSystemException e) {
			System.out.println("concurrent commit, waiting random interval");
			Thread.sleep((long) (2000 * Math.random()));
			cameraRepo.save(cam);
		}
		
	}
}
