package com.dochub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.PatientDTO;
import com.dochub.entity.Patient;
import com.dochub.service.PatientService;

@RestController
@RequestMapping("patient")
public class PatientController
{
    @Autowired
    private PatientService patientService;
    @Autowired
    private Transformer    transformer;

    @PostMapping(value = "/add")
    public PatientDTO add(@RequestBody PatientDTO patientDTO)
    {
        if (patientDTO == null)
            throw new IllegalStateException("Patient is null");
        Patient patient = transformer.transform(patientDTO);
        patient = patientService.add(patient);
        return transformer.transform(patient);
    }

    @GetMapping(value = "/listall")
    public List<PatientDTO> listAll(Pageable pageable)
    {
        Page<Patient> list = patientService.listAll(pageable);
        List<PatientDTO> listDTO = new ArrayList<>();
        list.forEach(patient -> listDTO.add(transformer.transform(patient)));
        return listDTO;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        patientService.deleteById(id);
    }

    @GetMapping("/{id}")
    public PatientDTO one(@PathVariable Long id)
    {
        PatientDTO dto = transformer.transform(patientService.one(id));
        return dto;
    }
}
