package com.userportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.userportal.model.ImageModel;
import com.userportal.service.StorageService;

@RestController
@RequestMapping({ "/user" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class UploadController {

	@Autowired
	StorageService storageService;

	@PostMapping("/imageUpload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("id") long id) {
		String message = "";
		try {
			storageService.store(file, id);

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/imageByID/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable long id) {
		try {
			ImageModel im = storageService.loadFile(id);
			
			HttpHeaders headers = new HttpHeaders();
			 headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+im.getName()+ "\"");

			 headers.add(HttpHeaders.CONTENT_LENGTH, Integer.toString(im.getPic().length));
			if (im.getType() != null) {
				headers.setContentType(MediaType.valueOf(im.getType()));
			} else {
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			}
			Resource resource = new ByteArrayResource(im.getPic());
			
			return ResponseEntity.ok().headers(headers).body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=").body(null);
		}

	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<String> getListFiles(@PathVariable long id) {
		String fileNames = MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", id).build().toString();

		return ResponseEntity.ok().body(fileNames);
	}

}