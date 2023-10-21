package com.femfy.femfyapi.domain.interfaces;

import java.util.List;

import com.femfy.femfyapi.delivery.dto.FileDTO;

public interface IFileService {
	public FileDTO insertFile(FileDTO fileDTO);
	public String deleteFile(FileDTO fileDTO);
	public List<FileDTO>findDocumentsByIdUser(Long idUser);
	public FileDTO getFileById(Long idFile);
}
