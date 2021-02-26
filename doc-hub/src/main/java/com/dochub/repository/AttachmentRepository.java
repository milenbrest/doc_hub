package com.dochub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dochub.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long>
{

}
