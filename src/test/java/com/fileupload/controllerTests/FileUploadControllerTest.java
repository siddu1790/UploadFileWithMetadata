package com.fileupload.controllerTests;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fileupload.controller.FileUploadController;
import com.fileupload.db.FileModel;
import com.fileupload.db.FileService;
import com.fileupload.db.FileUploadRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FileUploadControllerTest {

	@Mock
	private FileService fileService;

	@Autowired
	private WebApplicationContext wac;

	@Mock
	private Model model;
	
	@Mock
	private RedirectAttributes redirectAttributes;

	@InjectMocks
	private FileUploadController fileUploadController;

	private byte[] byteArr = "Any String you want".getBytes();
	private FileModel fileModel;
	private FileUploadRepository fileRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		fileModel = new FileModel("testtype", "testdesc", "filename.txt", byteArr);
	}

	@Test
	public void testWelcomePage() {
		Mockito.when(fileService.findAll()).thenReturn(Collections.singletonList(fileModel));
		String viewName = fileUploadController.welcome(model);
		assertEquals("FileUpload", viewName);
		Mockito.verify(model, Mockito.times(1)).addAttribute("files", Collections.singletonList(fileModel));
	}

	@Test
	public void testHandleFileUpload() throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		fileRepository = Mockito.mock(FileUploadRepository.class);
		redirectAttributes = Mockito.mock(RedirectAttributes.class);
		byte[] byteArr = file.getBytes();
		FileModel fileModel = new FileModel("testtype", "testdesc", file.getOriginalFilename(), byteArr);
		String viewName  = fileUploadController.handleFileUpload(file, "testtype", "testdesc", redirectAttributes);
		Mockito.verify(fileService, Mockito.times(1)).save(Mockito.refEq(fileModel));
		Mockito.verify(redirectAttributes, Mockito.times(1)).addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() +"!");
		assertEquals("redirect:/", viewName);
	}
}
