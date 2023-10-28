package com.femfy.femfyapi.domain.interfaces;

import java.util.List;

import com.femfy.femfyapi.delivery.dto.FileDTO;
import com.femfy.femfyapi.domain.entity.FileUser;

public interface IFileService {
	public FileUser insertFile(FileUser file);
	public String deleteFile(FileUser file);
	public List<FileUser>findDocumentsByIdUser(Long idUser);
	public FileUser getFileById(Long idFile);
}
