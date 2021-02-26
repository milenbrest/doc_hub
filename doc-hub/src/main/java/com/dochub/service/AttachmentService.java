package com.dochub.service;

import java.io.IOException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dochub.entity.Attachment;

public interface AttachmentService
{
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String upload(MultipartFile file, Long visitId) throws Exception;

    @Transactional(readOnly = true)
    public Attachment download(Long id) throws IOException;
}
