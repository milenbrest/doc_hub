package com.dochub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
            throw new IllegalStateException("Visit id cannot be null");
        if (patientId == null)
            throw new IllegalStateException("Patient id cannot be null");

        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new EntityNotFoundException(visitId));
        Patient patient = visit.getPatient();
        if (patient == null)
            throw new IllegalStateException(
                "No Patient for given visitId: " + visitId);
        if (patient.getId() != patientId)
            throw new IllegalStateException(
                "Visit :" + visitId + " is not owner by patient " + patientId);

        visitRepository.deleteById(visitId);
    }

}
