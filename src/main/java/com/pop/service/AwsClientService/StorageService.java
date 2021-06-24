package com.pop.service.AwsClientService;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
     String uploadFile(MultipartFile file, String fileName);

     String getMediaUrlFromFilename(String fileName);

     byte[] downloadFile(String fileName);

     String deleteFile(String fileName);
}
