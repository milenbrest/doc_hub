package com.dochub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dochub.entity.Rating;

public interface RatingRepository
        extends PagingAndSortingRepository<Rating, Long>
{
    Page<Rating> findByDoctorId(Pageable pageable, Long doctor);
}
