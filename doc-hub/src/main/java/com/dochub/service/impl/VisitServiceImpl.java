package com.dochub.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.dochub.entity.Day;
import com.dochub.entity.Doctor;
import com.dochub.entity.Patient;
import com.dochub.entity.Visit;
import com.dochub.entity.WorkingSchedule;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.VisitRepository;
import com.dochub.service.DayService;
import com.dochub.service.VisitService;

@Service
public class VisitServiceImpl implements VisitService
{
    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private DayService      dayService;

    @Override
    public Visit add(Visit visit)
    {
        if (visit == null)
            throw new IllegalStateException("Visit is null");
        Doctor doctor = visit.getDoctor();
        if (doctor == null)
            throw new IllegalStateException(
                "The visit must have a doctor, doctor is null");
        Patient patient = visit.getPatient();
        if (patient == null)
            throw new IllegalStateException(
                "The visit must have a patient, patient is null");
        if (visit.getDate() == null)
            throw new IllegalStateException(
                "The visit must have a date, date is null");
        // day by doctor and date
        Day day = dayService.findByDoctorAndDate(doctor, visit.getDate());
        if (day == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Not working day for doctor " + doctor.getId());
        List<WorkingSchedule> schedules = day.getSchedules();
        if (CollectionUtils.isEmpty(schedules))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Not working day for doctor " + doctor.getId());
        // find schedule for visit hour
        if (!new Date().before(visit.getFullDate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Visit can't be fixed in the past.");

        int visitHour = visit.getVisitHour();
        WorkingSchedule schedule = null;
        for (WorkingSchedule ws : schedules)
        {
            if (ws.getFromHour() <= visitHour && ws.getToHour() >= visitHour)
            {
                schedule = ws;
                break;
            }
        }
        if (schedule == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Not working day/hour for doctor " + doctor.getId());
        int visitDuration = schedule.getVisitDuration();
        if (visitHour % visitDuration != 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Invalid visit hour for  doctor " + doctor.getId());

        return visitRepository.save(visit);
    }

    @Override
    public List<Visit> listAll()
    {
        return (List<Visit>) visitRepository.findAll();
    }

    @Override
    public void deleteById(Long id)
    {
        visitRepository.deleteById(id);
    }

    @Override
    public Visit one(Long id)
    {
        return visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Page<Visit> listAll(Pageable pageable)
    {
        return visitRepository.findAll(pageable);
    }

    @Override
    public List<Visit> listAllAfterDate(Date date, Pageable pageable)
    {
        return visitRepository.findAllByDateAfter(pageable, date);
    }

}
