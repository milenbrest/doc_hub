package com.dochub.repository;

import org.springframework.data.repository.CrudRepository;

import com.dochub.entity.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long> {

}
