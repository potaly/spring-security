package com.potaly.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.potaly.dto.FileInfo;

/**
 * @author wang.qiang
 * @date 2019年8月13日
 */
@RestController
@RequestMapping("/file")
public class FileController {

	private String fileFold="F:\\java\\eclipse\\fileone\\potaly-security-demo\\src\\main\\java\\com\\potaly\\web\\controller";
	@PostMapping
	public FileInfo uploadFile(MultipartFile file) throws Exception, IOException {

		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		File localfile = new File(fileFold,new Date().getTime()+".txt");
		file.transferTo(localfile);
		return new FileInfo(localfile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void downloadFile(@PathVariable String id,HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException, IOException {
		try (InputStream input = new FileInputStream(new File(fileFold,id+".txt"));
			OutputStream out = response.getOutputStream();	
				) {
				response.setContentType("application/x-download");
				response.addHeader("Content-Disposition", "attachment;filename=test.txt");
				IOUtils.copy(input, out);
				out.flush();
			
		} 
	}
}
