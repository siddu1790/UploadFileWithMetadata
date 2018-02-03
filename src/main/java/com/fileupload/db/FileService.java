package com.fileupload.db;

import java.util.List;

public interface FileService {
	
	void save(FileModel fileModel);
	
	List<FileModel> findAll();

}
