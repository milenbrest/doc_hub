package com.dochub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.RatingDTO;
import com.dochub.entity.Rating;
import com.dochub.service.RatingService;

@RestController
@RequestMapping("rating")
public class RatingController
{
    @Autowired
    private RatingService ratingService;
    @Autowired
    private Transformer   transformer;

    @PostMapping(value = "/add")
    public RatingDTO add(@RequestBody RatingDTO ratingDTO)
    {
        if (ratingDTO == null)
            throw new IllegalStateException("Rating is null");
        Rating rating = transformer.transform(ratingDTO);
        rating = ratingService.add(rating);
        return transformer.transform(rating);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        ratingService.deleteById(id);
    }

    @GetMapping(value = "/listall")
    public List<RatingDTO> listAll(Pageable pageable)
    {
        Page<Rating> list = ratingService.listAll(pageable);
        List<RatingDTO> listDTO = new ArrayList<>();
        list.forEach(rating -> listDTO.add(transformer.transform(rating)));
        return listDTO;
    }

    @GetMapping("/{id}")
    public RatingDTO one(@PathVariable Long id)
    {
        RatingDTO dto = transformer.transform(ratingService.one(id));
        return dto;
    }

}
