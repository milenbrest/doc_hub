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

import com.dochub.dto.VisitDTO;
import com.dochub.entity.Visit;
import com.dochub.service.VisitService;

@RestController
public class VisitController {
	@Autowired
	private VisitService visitService;

	@PostMapping(value = "/visit/add")
	public VisitDTO add(@RequestBody VisitDTO visitDTO) {
		if (visitDTO == null)
			throw new IllegalStateException("Visit is null");
		Visit visit = transform(visitDTO);
		visit = visitService.add(visit);
		return transform(visit);
	}

	@GetMapping(value = "/visit/listall")
	public List<VisitDTO> listAll() {
		List<Visit> list = visitService.listAll();
		List<VisitDTO> listDTO = new ArrayList<>();
		for (Visit visit : list) {
			listDTO.add(transform(visit));
		}
		return listDTO;
	}

	@GetMapping("/visit/{id}")
	private VisitDTO one(@PathVariable Long id) {
		VisitDTO dto = transform(visitService.one(id));
		return dto;
	}

	@DeleteMapping(value = "/visit/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		visitService.deleteById(id);
	}

	protected Visit transform(VisitDTO dto) {
		Visit visit = new Visit();
		visit.setDay(dto.getDay());
		visit.setDoctor(dto.getDoctor());
		visit.setId(dto.getId());
		visit.setPatient(dto.getPatient());
		visit.setVisitHour(dto.getVisitHour());
		visit.setDate(dto.getDate());
		return visit;
	}

	protected VisitDTO transform(Visit visit) {
		VisitDTO dto = new VisitDTO();
		dto.setDay(visit.getDay());
		dto.setDoctor(visit.getDoctor());
		dto.setId(visit.getId());
		dto.setPatient(visit.getPatient());
		dto.setVisitHour(visit.getVisitHour());
		dto.setDate(visit.getDate());
		return dto;
	}
}
