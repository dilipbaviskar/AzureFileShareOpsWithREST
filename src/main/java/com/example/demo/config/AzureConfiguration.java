package com.example.demo.config;
 

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareClientBuilder;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.file.CloudFileClient;
 

import org.slf4j.Logger;
 
@Configuration
public class AzureConfiguration {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(AzureConfiguration.class);
	@Autowired
	private Environment environment;
	
	@Value("${azure.storage.ConnectionString}")
	String AZURE_CONNECTION_STRING;
	
	private final String REIS_FILE_SHARE_NAME="reis_data_share";
	
	@Bean ShareClient shareClient()
	{
		LOG.info("Azure Connection String : " +AZURE_CONNECTION_STRING );
		LOG.info("Azure File Share Name : " +REIS_FILE_SHARE_NAME );
		ShareClient shareClient = new ShareClientBuilder()
			    .connectionString(AZURE_CONNECTION_STRING).shareName(REIS_FILE_SHARE_NAME)
			    .buildClient();
		return shareClient;
	}
 
	@Bean
	public CloudBlobClient cloudBlobClient() throws URISyntaxException, StorageException, InvalidKeyException {
		CloudStorageAccount storageAccount = CloudStorageAccount
				.parse(environment.getProperty("azure.storage.ConnectionString"));
		return storageAccount.createCloudBlobClient();
	}

	@Bean
	public CloudFileClient cloudFileClient() throws URISyntaxException, StorageException, InvalidKeyException {
		CloudStorageAccount storageAccount = CloudStorageAccount
				.parse(environment.getProperty("azure.storage.ConnectionString"));
		return storageAccount.createCloudFileClient();
	}
	
	@Bean
	public CloudBlobContainer testBlobContainer() throws URISyntaxException, StorageException, InvalidKeyException {
		return cloudBlobClient().getContainerReference(environment.getProperty("azure.storage.container.name"));
	}	
	 
}
