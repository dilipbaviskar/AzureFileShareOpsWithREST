package com.example.demo.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service 
public interface BlobStorageService {
	 public URI uploadPicture(MultipartFile multipartFile);
}
