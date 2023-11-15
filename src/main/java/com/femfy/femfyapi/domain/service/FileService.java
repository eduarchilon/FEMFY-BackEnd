package com.femfy.femfyapi.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.FileUser;
import com.femfy.femfyapi.domain.entity.TypeStudy;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.interfaces.IFileService;
import com.femfy.femfyapi.domain.repository.FileRepository;

@Service
public class FileService implements IFileService {
	

	private final FileRepository fileRepository;

	public FileService(FileRepository fileRepository){
		this.fileRepository = fileRepository;
	}

	@Override
	public FileUser insertFile(FileUser file) {
		try {
			User user = new User();
			TypeStudy typeStudy = new TypeStudy();
			typeStudy.setId(file.getTypeStudy().getId());
			user.setId(file.getIdUser());
			FileUser fileUser = new FileUser();
			fileUser.setIdUser(file.getIdUser());
			fileUser.setFileExt(file.getFileExt());
			fileUser.setFileName(file.getFileName());
			fileUser.setStudyDate(file.getStudyDate());
			fileUser.setDescription(file.getDescription());
			fileUser.setTypeStudy(typeStudy);

			return fileRepository.save(fileUser);
		} catch (Exception e) {
			return file;
		}
		
	}
	
	@Override
	public String deleteFile(FileUser file) {
		try {
			fileRepository.deleteById(file.getId());
			return"OK";
		} catch (EntityNotFoundException e) {
            return "Error: El ID enviado es invalido";
        }
	}

	@Override
	public List<FileUser> findDocumentsByIdUser(Long idUser) {
		List<FileUser> fileUsers = new ArrayList<>();
		try {
			fileUsers =  fileRepository.findByIdUser(idUser);
		} catch (EntityNotFoundException e) {
			return fileUsers;
		}
		return fileUsers;
	}

	@Override
	public FileUser getFileById(Long idFile) {
		FileUser fileUser = new FileUser();
		try {
			fileUser = fileRepository.findById(idFile).orElse(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fileUser;
	}
}
