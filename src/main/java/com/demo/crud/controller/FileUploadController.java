package com.demo.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.crud.helper.FileUploadHelper;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHelper fileuploadhelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String>uploadFile(@RequestParam("file") MultipartFile file){
		
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize());
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());
		
		
		//validation
		if(file.isEmpty()) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file...");
		}
		//

//		if(!file.getContentType().equals("images/png")||
//			file.getContentType().equals("images/jpeg")||
//				file.getContentType().equals("images/jpg")
//		)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only png content type allowed...");
//		}

		//file upload code
		boolean f=fileuploadhelper.uploadFile(file);
		if(f) {
			return ResponseEntity.ok("File is successfully uploaded...");
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong ! try again");
	}

}
