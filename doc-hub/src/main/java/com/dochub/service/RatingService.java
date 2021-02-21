package com.dochub.service;

import java.util.List;

import com.dochub.entity.Rating;

public interface RatingService {
	public Rating add(Rating doctor);

	public List<Rating> listAll();

	void deleteById(Long id);

	public Rating one(Long id);
}
