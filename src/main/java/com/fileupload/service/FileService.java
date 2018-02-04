package com.fileupload.service;

import java.util.List;

import com.fileupload.domain.FileModel;

public interface FileService {
	
	void save(FileModel fileModel);
	
	List<FileModel> findAll();

}
