package com.dochub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.DoctorDTO;
import com.dochub.dto.RatingDTO;
import com.dochub.dto.WorkingScheduleDTO;
import com.dochub.entity.Doctor;
import com.dochub.entity.Rating;
import com.dochub.entity.WorkingSchedule;
import com.dochub.service.DoctorService;

@RestController
@RequestMapping("doctor")
public class DoctorController
{
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private Transformer   transformer;

    @PostMapping(value = "/add")
    public DoctorDTO add(@RequestBody DoctorDTO doctorDTO)
    {
        if (doctorDTO == null)
            throw new IllegalStateException("Doctor is null");
        Doctor doctor = transformer.transform(doctorDTO);
        doctor = doctorService.add(doctor);
        return transformer.transform(doctor);
    }

    @GetMapping(value = "/list")
    public List<DoctorDTO> listAll(Pageable pageable)
    {
        Page<Doctor> list = doctorService.listAll(pageable);
        List<DoctorDTO> listDTO = new ArrayList<>();
        list.forEach(doctor -> listDTO.add(transformer.transform(doctor)));
        return listDTO;
    }

    @GetMapping("/{id}")
    public DoctorDTO one(@PathVariable Long id)
    {
        DoctorDTO dto = transformer.transform(doctorService.one(id));
        return dto;
    }

    @GetMapping("/rating/{id}")
    public List<RatingDTO> getRatings(@PathVariable Long id, Pageable pageable)
    {
        List<RatingDTO> ratingsDTO = new ArrayList<>();
        Page<Rating> ratings = doctorService.findRatingsByDoctor(pageable, id);
        ratings.forEach(
            rating -> ratingsDTO.add(transformer.transform(rating)));
        return ratingsDTO;
    }

    @GetMapping("/schedule/{id}")
    public List<WorkingScheduleDTO> getSchedule(
        @PathVariable Long id,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
        List<WorkingScheduleDTO> workingScheduleDTO = new ArrayList<>();
        List<WorkingSchedule> scheduleList = doctorService
                .findScheduleByDate(id, date);
        scheduleList.forEach(schedule -> workingScheduleDTO
                .add(transformer.transform(schedule)));
        return workingScheduleDTO;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        doctorService.deleteById(id);
    }
}
