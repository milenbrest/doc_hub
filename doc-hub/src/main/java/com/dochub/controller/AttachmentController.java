package com.dochub.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.dochub.entity.Attachment;
import com.dochub.service.AttachmentService;

@RestController
@RequestMapping("attachment")
public class AttachmentController
{
    @Autowired
    private AttachmentService attachmentService;

    @PostMapping(value = "/add")
    public String uploadImage(
        @RequestParam MultipartFile file,
        @RequestParam Long visitId) throws Exception
    {
        if (file == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Attachment is null");
        if (visitId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Cannot upload attachment to null visit");

        return attachmentService.upload(file, visitId);
    }

    @SuppressWarnings("resource")
    @GetMapping(value = "/download/{id}", produces = MediaType.ALL_VALUE)
    public @ResponseBody ResponseEntity<InputStreamResource> downloadImage(
        @PathVariable Long id) throws IOException
    {
        Attachment attachment = attachmentService.download(id);
        File file = new File(attachment.getPath() + attachment.getName());
        InputStreamResource resource = new InputStreamResource(
            new FileInputStream(file));
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(MediaType.IMAGE_JPEG)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}
