package com.femfy.femfyapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.FileUser;
import com.femfy.femfyapi.repository.FileRepository;

import dto.FileDTO;

@Service
public class FileService implements IFileService{
	
	@Autowired
	FileRepository fileRepository;

	@Override
	public FileDTO insertFile(FileDTO fileDTO) {
		try {
			FileUser fileUser = new FileUser();
			fileUser.setIdUser(fileDTO.getIdUser());
			fileUser.setFileExt(fileDTO.getFileExt());
			fileUser.setFileName(fileDTO.getFileName());
			fileRepository.save(fileUser);
			return fileDTO;
		} catch (Exception e) {
			return fileDTO;
		}
		
	}
	
	@Override
	public String deleteFile(FileDTO fileDTO) {
		String result ="";
		long deletedRecords = fileRepository.deleteByIdUserAndFileName(fileDTO.getIdUser(),fileDTO.getFileName());
		if(deletedRecords > 0) {
			result ="OK";
		}else {
			result ="Error";
		}
	
		return result;
	}

	@Override
	public List<FileDTO> findDocumentsByIsUser(Long idUser) {
		List<FileDTO> documents = new ArrayList<>();
		List<FileUser> fileUsers = new ArrayList<>();
		try {
			fileUsers = fileRepository.findByIdUser(idUser);
			
			for (FileUser fileUser : fileUsers) {
				FileDTO dto = new FileDTO();
				dto.setIdUser(fileUser.getIdUser());
				dto.setFileName(fileUser.getFileName());
				dto.setFileExt(fileUser.getFileExt());
				
				documents.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return documents;
	}
}
