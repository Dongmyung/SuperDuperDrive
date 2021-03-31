package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean isFileNameAvailable(String fileName, Integer userId) {
        return fileMapper.getFileByFileNameAndUserId(fileName, userId) == null;
    }

    public List<UserFile> getFilesByUserId(Integer userId) {
        return fileMapper.getFilesByUserId(userId);
    }

    public UserFile getFileByFileId(Integer fileId) {
        return fileMapper.getFileByFileId(fileId);
    }

    public int saveInDatabase(MultipartFile fileUpload, Integer userId) throws IOException {
        UserFile file = new UserFile(
                null,
                fileUpload.getOriginalFilename(),
                fileUpload.getContentType(),
                Long.toString(fileUpload.getSize()),
                userId,
                fileUpload.getBytes()
        );
        return fileMapper.saveFile(file);
    }

    public void deleteFileById(Integer fileId) {
        fileMapper.deleteFileByFileId(fileId);
    }

    public void deleteFileByIdAndUserId(Integer fileId, Integer userId) {
        fileMapper.deleteFileByFileIdAndUserId(fileId, userId);
    }
}
