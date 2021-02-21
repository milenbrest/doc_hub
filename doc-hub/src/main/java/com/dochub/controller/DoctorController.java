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

import com.dochub.dto.DoctorDTO;
import com.dochub.entity.Doctor;
import com.dochub.service.DoctorService;

@RestController
public class DoctorController {
	@Autowired
	private DoctorService doctorService;

	@PostMapping(value = "/doctor/add")
	public DoctorDTO add(@RequestBody DoctorDTO doctorDTO) {
		if (doctorDTO == null)
			throw new IllegalStateException("Doctor is null");
		Doctor doctor = transform(doctorDTO);
		doctor = doctorService.add(doctor);
		return transform(doctor);
	}

	@GetMapping(value = "/doctor/listall")
	public List<DoctorDTO> listAll() {
		List<Doctor> list = doctorService.listAll();
		List<DoctorDTO> listDTO = new ArrayList<>();
		for (Doctor doctor : list) {
			listDTO.add(transform(doctor));
		}
		return listDTO;
	}

	@GetMapping("/doctor/{id}")
	private DoctorDTO one(@PathVariable Long id) {
		DoctorDTO dto = transform(doctorService.one(id));
		return dto;
	}

	@DeleteMapping(value = "/doctor/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		doctorService.deleteById(id);
	}

	private Doctor transform(DoctorDTO doctorDTO) {
		Doctor doctor = new Doctor();
		doctor.setEducation(doctorDTO.getEducation());
		doctor.setEmail(doctorDTO.getEmail());
		doctor.setFirstName(doctorDTO.getFirstName());
		doctor.setId(doctorDTO.getId());
		doctor.setInfo(doctorDTO.getInfo());
		doctor.setLastName(doctorDTO.getLastName());
		doctor.setPhone(doctorDTO.getPhone());
		doctor.setQualification(doctorDTO.getQualification());
		return doctor;
	}

	private DoctorDTO transform(Doctor doctor) {
		DoctorDTO dto = new DoctorDTO();
		dto.setEducation(doctor.getEducation());
		dto.setEmail(doctor.getEmail());
		dto.setFirstName(doctor.getFirstName());
		dto.setId(doctor.getId());
		dto.setInfo(doctor.getInfo());
		dto.setLastName(doctor.getLastName());
		dto.setPhone(doctor.getPhone());
		dto.setQualification(doctor.getQualification());
		return dto;
	}
}
