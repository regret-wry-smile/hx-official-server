package com.hx.external.service;



import com.hx.external.domain.External;
import com.hx.external.domain.ExternalDTO;
import com.hx.external.domain.Module;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExternalService {

    External UploadExternal(MultipartFile file);

    void insertExternal(External external);

    List SelectExternal(List<Module> modules);

    List<ExternalDTO> selectByPage(ExternalDTO externalDTO);

    int count(ExternalDTO externalDTO);

    void deleteExternal(External external);

    void deleteExternals(int[] ids);

    void updateExternal(External external);
}
