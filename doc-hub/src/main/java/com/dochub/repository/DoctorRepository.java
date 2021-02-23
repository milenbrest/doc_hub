package com.dochub.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dochub.entity.Doctor;

public interface DoctorRepository
        extends PagingAndSortingRepository<Doctor, Long>
{

}
