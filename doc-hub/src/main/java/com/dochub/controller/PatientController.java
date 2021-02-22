package com.dochub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.PatientDTO;
import com.dochub.entity.Patient;
import com.dochub.service.PatientService;

@RestController
public class PatientController
{
    @Autowired
    private PatientService patientService;
    @Autowired
    private Transformer    transformer;

    @PostMapping(value = "/patient/add")
    public PatientDTO add(@RequestBody PatientDTO patientDTO)
    {
        if (patientDTO == null)
            throw new IllegalStateException("Patient is null");
        Patient patient = transformer.transform(patientDTO);
        patient = patientService.add(patient);
        return transformer.transform(patient);
    }

    @GetMapping(value = "/patient/listall")
    public List<PatientDTO> listAll()
    {
        List<Patient> list = patientService.listAll();
        List<PatientDTO> listDTO = new ArrayList<>();
        list.forEach(patient -> listDTO.add(transformer.transform(patient)));
        return listDTO;
    }

    @DeleteMapping(value = "/patient/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        patientService.deleteById(id);
    }

    @GetMapping("/patient/{id}")
    private PatientDTO one(@PathVariable Long id)
    {
        PatientDTO dto = transformer.transform(patientService.one(id));
        return dto;
    }
}
