package com.userportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userportal.model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	
}