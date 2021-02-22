package com.dochub.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dochub.entity.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long>
{
    List<Rating> findByDoctorId(Long doctor);
}
