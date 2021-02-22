package com.dochub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dochub.entity.Rating;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.RatingRepository;
import com.dochub.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService
{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating add(Rating rating)
    {
        if (rating == null)
            throw new IllegalStateException("Rating is null");
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> listAll()
    {
        return (List<Rating>) ratingRepository.findAll();
    }

    @Override
    public void deleteById(Long id)
    {
        ratingRepository.deleteById(id);
    }

    @Override
    public Rating one(Long id)
    {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

}
