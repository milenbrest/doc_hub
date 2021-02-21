package com.dochub.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dochub.entity.Day;
import com.dochub.entity.Visit;
import com.dochub.exception.VisitNotFoundException;
import com.dochub.repository.VisitRepository;
import com.dochub.service.DayService;
import com.dochub.service.VisitService;

@Service
public class VisitServiceImpl implements VisitService {
	@Autowired
	private VisitRepository visitRepository;
	@Autowired
	private DayService dayService;

	@Override
	public Visit add(Visit visit) {
		if (visit == null)
			throw new IllegalStateException("Visit is null");
		Calendar c1 = Calendar.getInstance();
		c1.setTime(visit.getDate());
		// not working day, warn message is required
		if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Visit can't be fixed in not working days.");

		visit = visitRepository.save(visit);
		if (visit.getDay() == null) {
			Day day = new Day();
			day.setDate(visit.getDate());
			day.setDoctor(visit.getDoctor());
			List<Visit> visits = new ArrayList<>();
			visits.add(visit);
			day.setVisits(visits);
			dayService.add(day);
		}
		return visit;
	}

	@Override
	public List<Visit> listAll() {
		return (List<Visit>) visitRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

	@Override
	public Visit one(Long id) {
		return visitRepository.findById(id)
				.orElseThrow(() -> new VisitNotFoundException("Visit: " + id + " Not Found"));
	}

}
