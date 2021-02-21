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
public class PatientController {
	@Autowired
	private PatientService patientService;

	@PostMapping(value = "/patient/add")
	public PatientDTO add(@RequestBody PatientDTO patientDTO) {
		if (patientDTO == null)
			throw new IllegalStateException("Patient is null");
		Patient patient = transform(patientDTO);
		patient = patientService.add(patient);
		return transform(patient);
	}

	@GetMapping(value = "/patient/listall")
	public List<PatientDTO> listAll() {
		List<Patient> list = patientService.listAll();
		List<PatientDTO> listDTO = new ArrayList<>();
		for (Patient patient : list) {
			listDTO.add(transform(patient));
		}
		return listDTO;
	}

	@DeleteMapping(value = "/patient/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		patientService.deleteById(id);
	}

	@GetMapping("/patient/{id}")
	private PatientDTO one(@PathVariable Long id) {
		PatientDTO dto = transform(patientService.one(id));
		return dto;
	}

	private Patient transform(PatientDTO dto) {
		Patient patient = new Patient();
		patient.setEmail(dto.getEmail());
		patient.setFirstName(dto.getFirstName());
		patient.setId(dto.getId());
		patient.setLastName(dto.getLastName());
		patient.setPhone(dto.getPhone());
		return patient;
	}

	private PatientDTO transform(Patient patient) {
		PatientDTO dto = new PatientDTO();
		dto.setEmail(patient.getEmail());
		dto.setFirstName(patient.getFirstName());
		dto.setId(patient.getId());
		dto.setLastName(patient.getLastName());
		dto.setPhone(patient.getPhone());
		return dto;
	}
}
