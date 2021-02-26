package com.dochub.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dochub.entity.Doctor;
import com.dochub.entity.Rating;
import com.dochub.entity.WorkingSchedule;

public interface DoctorService
{
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Doctor add(Doctor doctor);

    @Transactional(readOnly = true)
    public Page<Doctor> listAll(Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteById(Long id);

    @Transactional(readOnly = true)
    public Doctor one(Long id);

    @Transactional(readOnly = true)
    public Page<Rating> findRatingsByDoctor(Pageable pageable, Long id);

    @Transactional(readOnly = true)
    public List<WorkingSchedule> findScheduleByDate(Long id, Date date);
}
