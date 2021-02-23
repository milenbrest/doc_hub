package com.dochub.service.impl;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dochub.entity.Doctor;
import com.dochub.entity.Patient;
import com.dochub.entity.Visit;
import com.dochub.exception.EntityNotFoundException;
import com.dochub.repository.VisitRepository;
import com.dochub.service.VisitService;
import com.dochub.utils.VisitHourInterval;

@Service
public class VisitServiceImpl implements VisitService
{
    @Autowired
    private VisitRepository visitRepository;

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

        VisitHourInterval visitHour = visit.getVisitHour();
        VisitHourInterval workingFrom = doctor.getWorkingFrom();
        VisitHourInterval workingTo = doctor.getWorkingTo();
        Calendar visitCalendar = Calendar.getInstance();
        visitCalendar.setTime(visit.getDate());
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date());

        if (!isInTheFuture(nowCalendar, visitCalendar, visitHour))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Visit can't be fixed in the past.");

        List<DayOfWeek> notWorkingDays = doctor.getNotWorkingDays();

        notWorkingDays.forEach(day -> {
            if (visitCalendar.get(Calendar.DAY_OF_WEEK) == day.getValue())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Visit can't be fixed in doctor not working days.");
        });

        if (!visitHour.isBetween(workingFrom, workingTo))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Visit can't be fixed in doctor not working time.");

        // TODO: have other visit on this day/hour?
        visit = visitRepository.save(visit);
        return visit;
    }

    private boolean isInTheFuture(
        Calendar nowCalendar,
        Calendar visitCalendar,
        VisitHourInterval visitHour)
    {
        visitCalendar.set(Calendar.HOUR_OF_DAY, visitHour.getHour());
        visitCalendar.set(Calendar.MINUTE, visitHour.getMinutes());
        visitCalendar.set(Calendar.MILLISECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        return nowCalendar.before(visitCalendar);
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
