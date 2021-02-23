package com.dochub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dochub.entity.Patient;

public interface PatientService
{
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Patient add(Patient doctor);

    @Transactional(readOnly = true)
    public List<Patient> listAll();

    @Transactional(readOnly = true)
    public Page<Patient> listAll(Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    void deleteById(Long id);

    @Transactional(readOnly = true)
    public Patient one(Long id);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void cancelVisit(Long patientId, Long visitId);
}
