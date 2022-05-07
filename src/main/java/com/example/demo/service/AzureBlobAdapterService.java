package com.example.demo.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareClientBuilder;
import com.azure.storage.file.share.ShareDirectoryClient;
import com.azure.storage.file.share.ShareFileClient;
import com.azure.storage.file.share.ShareFileClientBuilder;

@Service
public class AzureBlobAdapterService {
	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(AzureBlobAdapterService.class);

	@Value("${azure.storage.ConnectionString}")
	String AZURE_CONNECTION_STRING;
	/*
	 * File share client
	 */

	public Boolean createNewFileShare(String newShareName) {

		try {
			ShareClient shareClient = new ShareClientBuilder().connectionString(AZURE_CONNECTION_STRING)
					.shareName(newShareName).buildClient();

			shareClient.create();
			return true;
		} catch (Exception e) {
			LOG.error("createFileShare exception: ", e);
			return false;
		}
	}

	public boolean createFileShareDirectory(String shareName, String dirName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(AZURE_CONNECTION_STRING)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			dirClient.create();
			return true;
		} catch (Exception e) {
			System.out.println("createDirectory exception: " + e.getMessage());
			return false;
		}
	}

	public Boolean enumerateFilesAndDirs(String shareName, String dirName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(AZURE_CONNECTION_STRING)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			dirClient.listFilesAndDirectories().forEach(fileRef -> System.out.printf("Resource: %s\t Directory? %b\n",
					fileRef.getName(), fileRef.isDirectory()));

			return true;
		} catch (Exception e) {
			System.out.println("enumerateFilesAndDirs exception: " + e.getMessage());
			return false;
		}
	}

	public Boolean deleteFileShare(String shareName) {
		try {
			ShareClient shareclient = new ShareClientBuilder().connectionString(AZURE_CONNECTION_STRING)
					.shareName(shareName).buildClient();

			shareclient.delete();
			return true;
		} catch (Exception e) {
			LOG.error("deleteFileShare exception: ", e);
			return false;
		}
	}

	public boolean uploadFile(String connectStr, String shareName, String dirName, String fileName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(AZURE_CONNECTION_STRING)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			ShareFileClient fileClient = dirClient.getFileClient(fileName);
			fileClient.create(1024);
			fileClient.uploadFromFile(fileName);
			return true;
		} catch (Exception e) {
			LOG.error("uploadFile exception: " + e.getMessage());
			return false;
		}
	}

}
