package com.dochub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dochub.entity.Doctor;
import com.dochub.exception.DoctorNotFoundException;
import com.dochub.repository.DoctorRepository;
import com.dochub.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Doctor add(Doctor doctor) {
		if (doctor == null)
			throw new IllegalStateException("Doctor is null");
		return doctorRepository.save(doctor);
	}

	@Override
	public List<Doctor> listAll() {
		return (List<Doctor>) doctorRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		doctorRepository.deleteById(id);
	}

	@Override
	public Doctor one(Long id) {
		return doctorRepository.findById(id)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor: " + id + " Not Found"));
	}

}
