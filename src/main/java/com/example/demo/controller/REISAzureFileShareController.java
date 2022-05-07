package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.AzureBlobAdapterService;

@RestController
@RequestMapping("/app/v1/blobstorage")
public class REISAzureFileShareController {
	private AzureBlobAdapterService azureBlobAdapter;

	public REISAzureFileShareController(AzureBlobAdapterService adapter) {
		this.azureBlobAdapter = adapter;
	}
	
	@GetMapping("/isRunning")
	public ResponseEntity<String> isRunning() {
		 
		return ResponseEntity.ok("REIS Azure Blob API APplication Running");
	}

 
	
	@PostMapping("/fileshare/{fileShareName}")
	public ResponseEntity<Boolean> createFileShare(@PathVariable String fileShareName) {
		boolean created = azureBlobAdapter.createNewFileShare(fileShareName);
		return ResponseEntity.ok(created);
	}
	
	@PostMapping("/fileshare/{fileShareName}/{direcotryName}")
	public ResponseEntity<Boolean> createFileShareDirectory(@PathVariable String fileShareName , @PathVariable String direcotryName) {
		
		boolean created = azureBlobAdapter.createFileShareDirectory(fileShareName,direcotryName);
		return ResponseEntity.ok(created);
	}
	
	
	@PostMapping("/fileshare/listall/{fileShareName}")
	public ResponseEntity<Boolean> enumerateAllDirectories(@PathVariable String fileShareName) {
		boolean created = azureBlobAdapter.enumerateFilesAndDirs(fileShareName,"2021");
		return ResponseEntity.ok(created);
	}

 
	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
	        throws MultipartException, IllegalStateException {

	    if (file != null && file.getContentType() != null && !file.getContentType().toLowerCase().startsWith("image"))
	        throw new MultipartException("not img");

	    // code to actual file storage
	    //storageService.store(file);
	    redirectAttributes.addFlashAttribute("message",
	            "You successfully uploaded " + file.getOriginalFilename() + "!");

	    return "redirect:/";
	}
	   
	    
//	@PostMapping
//	public ResponseEntity upload(@RequestParam MultipartFile multipartFile) {
//		URI url = azureBlobAdapter.upload(multipartFile);
//		return ResponseEntity.ok(url);
////	}
//
//	@GetMapping("/blobs")
//	public ResponseEntity<List<?>> getAllBlobs(@RequestParam String containerName) {
//		List<?> uris = azureBlobAdapter.listBlobs(containerName);
//		return ResponseEntity.ok(uris);
//	}
//	
//
//	@GetMapping("/fileshares")
//	public ResponseEntity<List<?>> getAllFileShares(@RequestParam String containerName) {
//		List<?> uris = azureBlobAdapter.listBlobs(containerName);
//		return ResponseEntity.ok(uris);
//	}
//
//	@DeleteMapping
//	public ResponseEntity<?> delete(@RequestParam String containerName, @RequestParam String blobName) {
//		azureBlobAdapter.deleteBlob(containerName, blobName);
//		return ResponseEntity.ok().build();
//	}
}
