package com.dochub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dochub.entity.WorkingSchedule;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.WorkingScheduleRepository;
import com.dochub.service.WorkingScheduleService;

@Service
public class WorkingScheduleServiceImpl implements WorkingScheduleService
{
    @Autowired
    private WorkingScheduleRepository workingScheduleRepository;

    @Override
    public WorkingSchedule add(WorkingSchedule workingSchedule)
    {
        if (workingSchedule == null)
            throw new IllegalStateException("WorkingSchedule is null");
        return workingScheduleRepository.save(workingSchedule);
    }

    @Override
    public Page<WorkingSchedule> listAll(Pageable pageable)
    {
        return workingScheduleRepository.findAll(pageable);

    }

    @Override
    public void deleteById(Long id)
    {
        workingScheduleRepository.deleteById(id);
    }

    @Override
    public WorkingSchedule one(Long id)
    {
        return workingScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

}
