package com.dochub.repository;

import org.springframework.data.repository.CrudRepository;

import com.dochub.entity.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

}
