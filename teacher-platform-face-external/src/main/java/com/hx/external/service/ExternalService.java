package com.hx.external.service;



import com.hx.external.domain.External;
import com.hx.external.domain.ExternalDTO;
import com.hx.external.domain.Module;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ExternalService {

    External UploadExternal(MultipartFile file);

    void InsertExternal(External external);

    List SelectExternal(List<Module> modules);

    List<ExternalDTO> selectByPage(ExternalDTO externalDTO);

    int count(ExternalDTO externalDTO);

    void deleteModule(External external);

    void deleteModules(int[] ids);
}
