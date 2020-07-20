package com.anhtuan.store.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AmazonClient {
    public String uploadFile(MultipartFile multipartFile);
}
