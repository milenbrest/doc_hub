package com.dochub.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dochub.entity.Day;
import com.dochub.entity.Doctor;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.DayRepository;
import com.dochub.service.DayService;

@Service
public class DayServiceImpl implements DayService
{
    @Autowired
    private DayRepository dayRepository;

    @Override
    public Day add(Day day)
    {
        if (day == null)
            throw new IllegalStateException("Day is null");
        return dayRepository.save(day);
    }

    @Override
    public Page<Day> listAll(Pageable pageable)
    {
        return dayRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id)
    {
        dayRepository.deleteById(id);
    }

    @Override
    public Day one(Long id)
    {
        return dayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Day findByDoctorAndDate(Doctor doctor, Date date)
    {
        return dayRepository.findDayByDoctorAndDate(doctor, date);
    }

}
