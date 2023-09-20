package com.femfy.femfyapi.service;

import java.util.List;

import dto.FileDTO;

public interface IFileService {
	public FileDTO insertFile(FileDTO fileDTO);
	public String deleteFile(FileDTO fileDTO);
	public List<FileDTO>findDocumentsByIsUser(Long idUser);
}
