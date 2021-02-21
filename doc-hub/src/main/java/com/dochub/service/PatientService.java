package com.dochub.service;

import java.util.List;

import com.dochub.entity.Patient;

public interface PatientService {
	public Patient add(Patient doctor);

	public List<Patient> listAll();

	void deleteById(Long id);

	public Patient one(Long id);
}
