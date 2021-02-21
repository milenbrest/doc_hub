package com.dochub.repository;

import org.springframework.data.repository.CrudRepository;

import com.dochub.entity.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
