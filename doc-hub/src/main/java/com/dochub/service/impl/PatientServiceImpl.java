package com.dochub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dochub.entity.Patient;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.PatientRepository;
import com.dochub.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService
{
    @Autowired
    private PatientRepository patientRepository;

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

}
