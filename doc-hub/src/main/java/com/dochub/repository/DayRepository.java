package com.dochub.repository;

import java.util.Date;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dochub.entity.Day;
import com.dochub.entity.Doctor;

public interface DayRepository extends PagingAndSortingRepository<Day, Long>
{
    Day findDayByDoctorAndDate(Doctor doctor, Date date);
}
