package com.dochub.service;

import java.util.List;

import com.dochub.entity.Visit;

public interface VisitService {
	public Visit add(Visit visit);

	public List<Visit> listAll();

	public void deleteById(Long id);

	public Visit one(Long id);
}
