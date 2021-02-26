package com.dochub.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dochub.entity.WorkingSchedule;

public interface WorkingScheduleService
{
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public WorkingSchedule add(WorkingSchedule workingSchedule);

    @Transactional(readOnly = true)
    public Page<WorkingSchedule> listAll(Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteById(Long id);

    @Transactional(readOnly = true)
    public WorkingSchedule one(Long id);
}
