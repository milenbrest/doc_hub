package com.dochub.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dochub.entity.Patient;
import com.dochub.entity.Visit;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.PatientRepository;
import com.dochub.repository.VisitRepository;
import com.dochub.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService
{
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VisitRepository   visitRepository;

    @Override
    public Patient add(Patient patient)
    {
        if (patient == null)
            throw new IllegalStateException("Patient is null");
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> listAll()
    {
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public Page<Patient> listAll(Pageable pageable)
    {

        return patientRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id)
    {
        patientRepository.deleteById(id);

    }

    @Override
    public Patient one(Long id)
    {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void cancelVisit(Long patientId, Long visitId)
    {
        if (visitId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Visit id cannot be null");
        if (patientId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Patient id cannot be null");

        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new EntityNotFoundException(visitId));
        Patient patient = visit.getPatient();
        if (patient == null)
            throw new IllegalStateException(
                "No Patient for given visitId: " + visitId);
        if (patient.getId() != patientId)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Visit :" + visitId + " is not owner by patient " + patientId);
        if (new Date().after(visit.getFullDate()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "Passed visits cannot be deleted.");
        visitRepository.deleteById(visitId);
    }

}
