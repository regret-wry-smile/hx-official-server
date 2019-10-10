package com.hx.external.service;



import com.hx.external.domain.External;
import com.hx.external.domain.Module;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ExternalService {

    External UploadExternal(MultipartFile file);

    void InsertExternal(External external);

    HashMap SelectExternal(List<Module> modules);
}
