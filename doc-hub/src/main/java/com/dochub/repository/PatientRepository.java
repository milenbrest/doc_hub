package com.dochub.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dochub.entity.Patient;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {

}
