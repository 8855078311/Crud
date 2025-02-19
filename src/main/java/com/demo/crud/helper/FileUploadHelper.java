package com.demo.crud.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	public final String UPLOAD_DIR="E:\\crud\\src\\main\\resources\\static\\images";
	
	
	public boolean uploadFile(MultipartFile multipartFile) {
		boolean f1=false;
		try {
			//
			
//			InputStream is=multipartFile.getInputStream();
//			byte data[]=new byte[is.available()];
//			is.read(data);
//			
//			//write
//			FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+ File.separator +multipartFile.getOriginalFilename());
//		
//			fos.write(data);
//			
//			fos.flush();
//			fos.close();
//			f=true;
			
			Files.copy(multipartFile.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			f1=true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f1;
	}
}
