package com.dochub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dochub.entity.Doctor;
import com.dochub.entity.Rating;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.DoctorRepository;
import com.dochub.repository.RatingRepository;
import com.dochub.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService
{
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Doctor add(Doctor doctor)
    {
        if (doctor == null)
            throw new IllegalStateException("Doctor is null");
        return doctorRepository.save(doctor);
    }

    @Override
    public Page<Doctor> listAll(Pageable pageable)
    {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id)
    {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor one(Long id)
    {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Page<Rating> findRatingsByDoctor(Pageable pageable, Long id)
    {
        if (id == null)
            throw new IllegalStateException("Doctor id is null");
        return ratingRepository.findByDoctorId(pageable, id);
    }

}
