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

import com.dochub.dto.DoctorDTO;
import com.dochub.dto.RatingDTO;
import com.dochub.entity.Doctor;
import com.dochub.entity.Rating;
import com.dochub.service.DoctorService;

@RestController
public class DoctorController
{
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private Transformer   transformer;

    @PostMapping(value = "/doctor/add")
    public DoctorDTO add(@RequestBody DoctorDTO doctorDTO)
    {
        if (doctorDTO == null)
            throw new IllegalStateException("Doctor is null");
        Doctor doctor = transformer.transform(doctorDTO);
        doctor = doctorService.add(doctor);
        return transformer.transform(doctor);
    }

    @GetMapping(value = "/doctor/listall")
    public List<DoctorDTO> listAll()
    {
        List<Doctor> list = doctorService.listAll();
        List<DoctorDTO> listDTO = new ArrayList<>();
        list.forEach(doctor -> listDTO.add(transformer.transform(doctor)));
        return listDTO;
    }

    @GetMapping("/doctor/{id}")
    private DoctorDTO one(@PathVariable Long id)
    {
        DoctorDTO dto = transformer.transform(doctorService.one(id));
        return dto;
    }

    @GetMapping("/doctor/rating/{id}")
    private List<RatingDTO> getRatings(@PathVariable Long id)
    {
        List<RatingDTO> ratingsDTO = new ArrayList<>();
        List<Rating> ratings = doctorService.findRatingsByDoctor(id);
        ratings.forEach(
            rating -> ratingsDTO.add(transformer.transform(rating)));
        return ratingsDTO;
    }

    @DeleteMapping(value = "/doctor/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        doctorService.deleteById(id);
    }
}
