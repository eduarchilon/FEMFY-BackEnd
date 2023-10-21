package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.delivery.dto.FileDTO;

public interface IUploadFileService {
	public String uploadFile(FileDTO fileDTO);
	public FileDTO downloadFile(FileDTO fileDTO);
	public String deleteFile(FileDTO fileDTO);
}
