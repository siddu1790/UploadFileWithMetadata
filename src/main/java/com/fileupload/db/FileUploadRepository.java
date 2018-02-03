package com.fileupload.db;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface FileUploadRepository extends JpaRepository<FileModel, Integer>{
	
}
