package com.dochub.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dochub.entity.Day;
import com.dochub.entity.Doctor;

public interface DayService
{
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Day add(Day day);

    @Transactional(readOnly = true)
    public Page<Day> listAll(Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteById(Long id);

    @Transactional(readOnly = true)
    public Day one(Long id);

    @Transactional(readOnly = true)
    public Day findByDoctorAndDate(Doctor doctor, Date date);
}
