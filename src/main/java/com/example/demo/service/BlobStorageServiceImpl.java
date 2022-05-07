package com.example.demo.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class BlobStorageServiceImpl implements BlobStorageService {
	 private final Logger LOGGER = LoggerFactory.getLogger(BlobStorageServiceImpl.class);
	    private final CloudBlobContainer cloudBlobContainer;

	@Autowired
	public BlobStorageServiceImpl(CloudBlobContainer cloudBlobContainer) {
		this.cloudBlobContainer = cloudBlobContainer;
		
	}

	@Override
	public URI uploadPicture(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

//	@Override
//	public URI uploadPicture(MultipartFile multipartFile) {
//		 URI uri;
//	        String multipartName = multipartFile.getName().replaceAll("[\n|\r|\t]", "_");
//	        LOGGER.info("Profile image uploading, image name: {}", multipartName);
//
//	        try {
//	            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//	            String fileName = String.join(".", UUID.randomUUID().toString(), extension);
//	            CloudBlockBlob  blob = cloudBlobContainer.getBlockBlobReference(fileName);
//	            blob.upload(multipartFile.getInputStream(), -1);
//	            uri = blob.getUri();
//	        } catch (URISyntaxException | StorageException | IOException e) {
//	            throw new UploadPictureFailedException();
//	        }
//	        return Optional.ofNullable(uri).orElseThrow(UploadPictureFailedException::new);
//	}

}
