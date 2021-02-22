package com.dochub.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dochub.entity.Day;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.DayRepository;
import com.dochub.service.DayService;

@Service
public class DayServiceImpl implements DayService
{
    @Autowired
    private DayRepository dayRepository;

    @Override
    public Day add(Day day)
    {
        if (day == null)
            throw new IllegalStateException("Day is null");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(day.getDate());
        if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                || (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
            day.setWorkingDay(false);
        return dayRepository.save(day);
    }

    @Override
    public List<Day> listAll()
    {
        return (List<Day>) dayRepository.findAll();
    }

    @Override
    public void deleteById(Long id)
    {
        dayRepository.deleteById(id);
    }

    @Override
    public Day one(Long id)
    {
        return dayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

}
