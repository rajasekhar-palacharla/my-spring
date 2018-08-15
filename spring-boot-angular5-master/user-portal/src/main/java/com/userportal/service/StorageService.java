package com.userportal.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.userportal.exception.FileStorageException;
import com.userportal.model.ImageModel;
import com.userportal.repository.ImageRepository;

@Service
public class StorageService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("upload-dir");

	@Autowired
	ImageRepository imageRepository;

	public void store(MultipartFile file, Long id) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			ImageModel im = new ImageModel(id, fileName, file.getContentType(), file.getBytes());
			imageRepository.save(im);
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public ImageModel loadFile(long id) throws FileNotFoundException {
		ImageModel im = imageRepository.findOne(id);
		if (im == null) {
			throw new FileNotFoundException();
		}

		return im;
	}

	public void delete(long id) {
		imageRepository.delete(id);
	}

	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}