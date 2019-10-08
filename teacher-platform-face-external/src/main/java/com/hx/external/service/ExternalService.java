package com.hx.external.service;



import com.hx.external.domain.External;
import org.springframework.web.multipart.MultipartFile;

public interface ExternalService {

    External UploadExternal(MultipartFile file);

    void InsertExternal(External external);
}
