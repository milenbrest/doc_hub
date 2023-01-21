package com.dochub.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dochub.entity.Doctor;
import com.dochub.entity.Rating;

public interface DoctorService {
	@Transactional(propagation = Propagation.REQUIRED)
	Doctor add(Doctor doctor);

	@Transactional(readOnly = true)
	Page<Doctor> listAll(Pageable pageable);

	@Transactional(propagation = Propagation.REQUIRED)
	void deleteById(Long id);

	@Transactional(readOnly = true)
	Doctor one(Long id);

	@Transactional(readOnly = true)
	Page<Rating> findRatingsByDoctor(Pageable pageable, Long id);
}
