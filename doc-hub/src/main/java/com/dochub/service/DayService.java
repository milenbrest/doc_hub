package com.dochub.service;

import java.util.List;

import com.dochub.entity.Day;

public interface DayService {
	public Day add(Day day);

	public List<Day> listAll();

	public void deleteById(Long id);

	public Day one(Long id);
}
