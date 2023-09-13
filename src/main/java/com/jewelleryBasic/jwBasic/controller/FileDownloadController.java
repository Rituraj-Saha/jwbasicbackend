package com.jewelleryBasic.jwBasic.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.FileCopyUtils;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/download")
public class FileDownloadController {
	 @Autowired
	 private ConfigurableEnvironment  myEnv;
	 
	 private String EXTERNAL_FILE_PATH = "";
	 @EventListener(ApplicationReadyEvent.class)
	 public void doSomethingAfterStartup() throws Exception {
	        EXTERNAL_FILE_PATH= myEnv.getProperty("filePath");
	 }
	 
	 @GetMapping("/welcome")
	 String fileTest() {
		 return "success";
	 } 
	 
	 @RequestMapping("/file/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
				@PathVariable("fileName") String fileName) throws IOException, java.io.IOException {
			
			File file = new File(EXTERNAL_FILE_PATH + fileName);
			System.out.println("external path outside: "+EXTERNAL_FILE_PATH+fileName);
			if (file.exists()) {
				//get the mimetype
				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				System.out.println("external path if exist: "+EXTERNAL_FILE_PATH);
				if (mimeType == null) {
					//unknown mimetype so set the mimetype to application/octet-stream
					mimeType = "application/octet-stream";
				}

				response.setContentType(mimeType);

				/**
				 * In a regular HTTP response, the Content-Disposition response header is a
				 * header indicating if the content is expected to be displayed inline in the
				 * browser, that is, as a Web page or as part of a Web page, or as an
				 * attachment, that is downloaded and saved locally.
				 * 
				 */

				/**
				 * Here we have mentioned it to show inline
				 */
				
				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

				 //Here we have mentioned it to show as attachment
				 //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				FileCopyUtils.copy(inputStream, response.getOutputStream());

			}
			else {
				System.out.println("File NOT Found");
			}
		}
}
