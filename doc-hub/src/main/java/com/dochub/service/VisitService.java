package com.dochub.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dochub.entity.Visit;

public interface VisitService
{
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Visit add(Visit visit);

    @Transactional(readOnly = true)
    public List<Visit> listAll();

    @Transactional(readOnly = true)
    public Page<Visit> listAll(Pageable pageable);

    @Transactional(readOnly = true)
    public List<Visit> listAllAfterDate(Date date, Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteById(Long id);

    @Transactional(readOnly = true)
    public Visit one(Long id);
}
