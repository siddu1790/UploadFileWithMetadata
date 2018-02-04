package com.fileupload.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fileupload.domain.FileModel;

@Transactional
@Repository
public interface FileUploadRepository extends JpaRepository<FileModel, Integer>{
	
}
