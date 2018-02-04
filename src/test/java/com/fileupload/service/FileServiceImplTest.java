package com.fileupload.service;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fileupload.domain.FileModel;
import com.fileupload.repository.FileUploadRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FileServiceImplTest {
    
    @Autowired
    private FileServiceImpl fileService;
    
    private FileModel fileModel;
    private FileUploadRepository fileRepository;
    private byte[] byteArr = "Any String you want".getBytes();
    
    @Before
    public void setUp() throws Exception {
    	fileModel = new FileModel("test","test","test", byteArr);
    	fileRepository = Mockito.mock(FileUploadRepository.class);
    	fileService = new FileServiceImpl(fileRepository);
    }
    
    @Test
    public void saveTest_Sucess(){
    	fileService.save(fileModel);
    	Mockito.verify(fileRepository, Mockito.times(1)).save(fileModel);
    }
    
    @Test
    public void listAllFilesTest_Sucess(){
    	Mockito.when(fileService.findAll()).thenReturn(Collections.singletonList(fileModel));
    	List<FileModel> filesList = fileService.findAll();
    	Mockito.verify(fileRepository, Mockito.times(1)).findAll();
    	assertEquals(1, filesList.size());
    }
}
