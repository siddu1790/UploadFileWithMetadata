package com.fileupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fileupload.domain.FileModel;
import com.fileupload.repository.FileUploadRepository;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	FileUploadRepository fileUploadRepository;
	
	public FileServiceImpl(FileUploadRepository fileUploadRepository) {
		this.fileUploadRepository = fileUploadRepository;
	}

	
	@Override
	public void save(FileModel fileModel) {
		fileUploadRepository.save(fileModel);		
	}

	@Override
	public List<FileModel> findAll() {
		return fileUploadRepository.findAll();
	}

}
