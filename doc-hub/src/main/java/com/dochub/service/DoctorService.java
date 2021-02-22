package com.dochub.service;

import java.util.List;

import com.dochub.entity.Doctor;
import com.dochub.entity.Rating;

public interface DoctorService {
	public Doctor add(Doctor doctor);

	public List<Doctor> listAll();

	public void deleteById(Long id);

	public Doctor one(Long id);
	
	public List<Rating> findRatingsByDoctor(Long id);
}
