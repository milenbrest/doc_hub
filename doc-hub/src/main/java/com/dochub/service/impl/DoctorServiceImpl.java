package com.dochub.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dochub.entity.Doctor;
import com.dochub.entity.Rating;
import com.dochub.entity.WorkingSchedule;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.DoctorRepository;
import com.dochub.repository.RatingRepository;
import com.dochub.repository.WorkingScheduleRepository;
import com.dochub.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService
{
    @Autowired
    private DoctorRepository          doctorRepository;
    @Autowired
    private RatingRepository          ratingRepository;
    @Autowired
    private WorkingScheduleRepository workingScheduleRepository;

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

    @Override
    public List<WorkingSchedule> findScheduleByDate(Long id, Date date)
    {
        System.out.println(id + " " + date.toString()   );
        return workingScheduleRepository.findAllByDoctorAndDate(id, date);
    }

}
