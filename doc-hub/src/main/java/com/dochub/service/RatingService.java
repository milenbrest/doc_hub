package com.dochub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dochub.entity.Rating;

public interface RatingService
{
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Rating add(Rating doctor);

    @Transactional(readOnly = true)
    public List<Rating> listAll();

    @Transactional(readOnly = true)
    public Page<Rating> listAll(Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    void deleteById(Long id);

    @Transactional(readOnly = true)
    public Rating one(Long id);
}
