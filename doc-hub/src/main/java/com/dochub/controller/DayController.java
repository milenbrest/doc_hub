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

import com.dochub.dto.DayDTO;
import com.dochub.dto.VisitDTO;
import com.dochub.entity.Day;
import com.dochub.entity.Visit;
import com.dochub.service.DayService;

@RestController
public class DayController {
	@Autowired
	private VisitController visitController;
	@Autowired
	private DayService dayService;

	@PostMapping(value = "/day/add")
	public DayDTO add(@RequestBody DayDTO dayDTO) {
		if (dayDTO == null)
			throw new IllegalStateException("Day is null");
		Day day = transform(dayDTO);
		day = dayService.add(day);
		return transform(day);
	}

	@GetMapping(value = "/day/listall")
	public List<DayDTO> listAll() {
		List<Day> list = dayService.listAll();
		List<DayDTO> listDTO = new ArrayList<>();
		for (Day day : list) {
			listDTO.add(transform(day));
		}
		return listDTO;
	}

	@GetMapping("/day/{id}")
	private DayDTO one(@PathVariable Long id) {
		DayDTO dto = transform(dayService.one(id));
		return dto;
	}

	@DeleteMapping(value = "/day/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		dayService.deleteById(id);
	}

	protected Day transform(DayDTO dto) {
		Day day = new Day();
		day.setDate(dto.getDate());
		day.setDoctor(dto.getDoctor());
		day.setId(dto.getId());
		day.setWorkingDay(dto.isWorkingDay());
		List<Visit> visits = new ArrayList<>();
		dto.getVisits().forEach(visitDTO -> visitController.transform(visitDTO));
		day.setVisits(visits);
		return day;
	}

	protected DayDTO transform(Day day) {
		DayDTO dto = new DayDTO();
		dto.setDate(day.getDate());
		dto.setDoctor(day.getDoctor());
		dto.setId(day.getId());
		dto.setWorkingDay(day.isWorkingDay());
		List<VisitDTO> visits = new ArrayList<>();
		day.getVisits().forEach(visit -> visitController.transform(visit));
		dto.setVisits(visits);
		return dto;
	}
}
