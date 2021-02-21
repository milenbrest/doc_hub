package com.dochub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.RatingDTO;
import com.dochub.entity.Rating;
import com.dochub.service.RatingService;

@RestController
public class RatingController {
	@Autowired
	private RatingService ratingService;

	@PostMapping(value = "/rating/add")
	public RatingDTO add(@RequestBody RatingDTO ratingDTO) {
		if (ratingDTO == null)
			throw new IllegalStateException("Rating is null");
		Rating rating = transform(ratingDTO);
		rating = ratingService.add(rating);
		return transform(rating);
	}

	@DeleteMapping(value = "/rating/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		ratingService.deleteById(id);
	}

	@GetMapping(value = "/rating/listall")
	public List<RatingDTO> listAll() {
		List<Rating> list = ratingService.listAll();
		List<RatingDTO> listDTO = new ArrayList<>();
		for (Rating rating : list) {
			listDTO.add(transform(rating));
		}
		return listDTO;
	}

	@GetMapping("/rating/{id}")
	private RatingDTO one(@PathVariable Long id) {
		RatingDTO dto = transform(ratingService.one(id));
		return dto;
	}

	private Rating transform(RatingDTO dto) {
		Rating rating = new Rating();
		rating.setComment(dto.getComment());
		rating.setDoctor(dto.getDoctor());
		rating.setId(dto.getId());
		rating.setPatient(dto.getPatient());
		rating.setRate(dto.getRate());
		return rating;
	}

	private RatingDTO transform(Rating rating) {
		RatingDTO dto = new RatingDTO();
		dto.setComment(rating.getComment());
		dto.setDoctor(rating.getDoctor());
		dto.setId(rating.getId());
		dto.setPatient(rating.getPatient());
		dto.setRate(rating.getRate());
		return dto;
	}
}
