package com.fileupload.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fileupload.db.FileModel;
import com.fileupload.db.FileService;

@Controller
public class FileUploadController {

	@Autowired
	FileService fileService;

	/*
	 * This controller will load the initial screen, with the file names and
	 * meta data in table format.
	 * 
	 * @param model, an Model interface.
	 * 
	 * @return String returns the home page to the user.
	 * 
	 */
	@GetMapping("/")
	public String welcome(Model model) {
		List<FileModel> filesList = fileService.findAll();
		model.addAttribute("files", filesList);
		return "FileUpload";
	}

	/*
	 * This controller will save the file and meta data to the h2 database.
	 * 
	 * @param file, multipart file which the user uploads
	 * 
	 * @param filetype, meta-data for the file
	 * 
	 * @param description, meta-data for the file
	 * 
	 * @param redirectAttributes, redirectAttributes interface for adding
	 * messages.
	 * 
	 * @return String, redirect to home page.
	 * 
	 */
	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "type", required = false) String fileType,
			@RequestParam(value = "description", required = false) String description,
			RedirectAttributes redirectAttributes) throws IOException {

		byte[] byteArr = file.getBytes();
		FileModel fileModel = new FileModel(fileType, description, file.getOriginalFilename(), byteArr);
		fileService.save(fileModel);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/";
	}
}
