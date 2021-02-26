package com.dochub.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.dochub.entity.Attachment;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.AttachmentRepository;
import com.dochub.service.AttachmentService;
import com.dochub.service.VisitService;

@Service
public class AttachmentServiceImpl implements AttachmentService
{
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private VisitService         visitService;
    @Value("/home/milen/Desktop/doc_hub data/files/")
    private String               path;

    @Override
    public String upload(MultipartFile file, Long visitId) throws Exception
    {
        if (file == null)
            throw new IllegalStateException("File is null");
        Path p = Paths.get(path);

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        filename = filename.toLowerCase().replaceAll(" ", "-");
        filename = filename + "_" + new Date().toString() + "_" + visitId;

        try
        {
            if (file.isEmpty())
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Failed to store empty filet");
            }
            if (filename.contains(".."))
            {
                // This is a security check
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot store file with relative path outside current directory ");
            }
            Files.copy(file.getInputStream(), p.resolve(filename),
                StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Failed to store file ", e);
        }
        Attachment attachment = new Attachment();
        attachment.setName(filename);
        attachment.setPath(path);
        attachment.setVisit(visitService.one(visitId));
        attachmentRepository.save(attachment);
        return filename;
    }

    @Override
    public Attachment download(Long id) throws IOException
    {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

/*        Path path = Paths.get(attachment.getPath() + attachment.getName());
        ByteArrayResource resource = new ByteArrayResource(
            Files.readAllBytes(path));*/
        return attachment;
    }

}
