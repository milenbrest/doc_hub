package com.dochub.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.dochub.dto.AttachmentDTO;
import com.dochub.dto.DayDTO;
import com.dochub.dto.DoctorDTO;
import com.dochub.dto.PatientDTO;
import com.dochub.dto.RatingDTO;
import com.dochub.dto.VisitDTO;
import com.dochub.dto.WorkingScheduleDTO;
import com.dochub.entity.Attachment;
import com.dochub.entity.Day;
import com.dochub.entity.Doctor;
import com.dochub.entity.Patient;
import com.dochub.entity.Rating;
import com.dochub.entity.Visit;
import com.dochub.entity.WorkingSchedule;
import com.dochub.service.AttachmentService;
import com.dochub.service.DayService;
import com.dochub.service.DoctorService;
import com.dochub.service.PatientService;
import com.dochub.service.VisitService;

import lombok.extern.slf4j.Slf4j;

@Component
public class Transformer
{
    @Autowired
    private DoctorService     doctorService;

    @Autowired
    private PatientService    patientService;

    @Autowired
    private DayService        dayService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private VisitService      visitService;

    protected Visit transform(VisitDTO dto)
    {
        Visit visit = new Visit();
        Long doctorId = dto.getDoctor();
        if (doctorId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Doctor id cannot be null");
        Doctor doctor = doctorService.one(doctorId);
        visit.setDoctor(doctor);
        visit.setId(dto.getId());
        Long patientId = dto.getPatient();
        if (patientId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Patient id cannot be null");
        Patient patient = patientService.one(patientId);
        visit.setPatient(patient);
        visit.setVisitHour(dto.getVisitHour());
        visit.setDate(dto.getDate());
        return visit;
    }

    protected VisitDTO transform(Visit visit)
    {
        VisitDTO dto = new VisitDTO();
        Doctor doctor = visit.getDoctor();
        if (doctor != null)
            dto.setDoctor(doctor.getId());
        dto.setId(visit.getId());
        Patient patient = visit.getPatient();
        if (patient != null)
            dto.setPatient(patient.getId());
        dto.setVisitHour(visit.getVisitHour());
        dto.setDate(visit.getDate());
        return dto;
    }

    protected Rating transform(RatingDTO dto)
    {
        Rating rating = new Rating();
        rating.setComment(dto.getComment());
        Long doctorId = dto.getDoctor();
        if (doctorId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Doctor id cannot be null");
        Doctor doctor = doctorService.one(doctorId);
        rating.setDoctor(doctor);
        rating.setId(dto.getId());
        Long patientId = dto.getPatient();
        if (patientId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Patient id cannot be null");
        Patient patient = patientService.one(patientId);
        rating.setPatient(patient);
        rating.setRate(dto.getRate());
        return rating;
    }

    protected RatingDTO transform(Rating rating)
    {
        RatingDTO dto = new RatingDTO();
        dto.setComment(rating.getComment());
        Doctor doctor = rating.getDoctor();
        if (doctor != null)
            dto.setDoctor(doctor.getId());
        dto.setId(rating.getId());
        Patient patient = rating.getPatient();
        if (patient != null)
            dto.setPatient(patient.getId());
        dto.setRate(rating.getRate());
        return dto;
    }

    protected Patient transform(PatientDTO dto)
    {
        Patient patient = new Patient();
        patient.setEmail(dto.getEmail());
        patient.setFirstName(dto.getFirstName());
        patient.setId(dto.getId());
        patient.setLastName(dto.getLastName());
        patient.setPhone(dto.getPhone());
        return patient;
    }

    protected PatientDTO transform(Patient patient)
    {
        PatientDTO dto = new PatientDTO();
        dto.setEmail(patient.getEmail());
        dto.setFirstName(patient.getFirstName());
        dto.setId(patient.getId());
        dto.setLastName(patient.getLastName());
        dto.setPhone(patient.getPhone());
        return dto;
    }

    protected Doctor transform(DoctorDTO doctorDTO)
    {
        Doctor doctor = new Doctor();
        doctor.setEducation(doctorDTO.getEducation());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setId(doctorDTO.getId());
        doctor.setInfo(doctorDTO.getInfo());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setQualification(doctorDTO.getQualification());

        List<DayDTO> scheduledDays = doctorDTO.getScheduledDays();
        if (!CollectionUtils.isEmpty(scheduledDays))
        {
            List<Day> days = new ArrayList<>();
            scheduledDays.forEach(dayDTO -> days.add(transform(dayDTO)));
            doctor.setScheduledDays(days);
        } else
            doctor.setScheduledDays(Collections.emptyList());
        return doctor;
    }

    protected DoctorDTO transform(Doctor doctor)
    {
        DoctorDTO dto = new DoctorDTO();
        dto.setEducation(doctor.getEducation());
        dto.setEmail(doctor.getEmail());
        dto.setFirstName(doctor.getFirstName());
        dto.setId(doctor.getId());
        dto.setInfo(doctor.getInfo());
        dto.setLastName(doctor.getLastName());
        dto.setPhone(doctor.getPhone());
        dto.setQualification(doctor.getQualification());
        List<Day> scheduledDays = doctor.getScheduledDays();
        if (!CollectionUtils.isEmpty(scheduledDays))
        {
            List<DayDTO> daysDTO = new ArrayList<>();
            scheduledDays.forEach(day -> daysDTO.add(transform(day)));
            dto.setScheduledDays(daysDTO);
        } else
            dto.setScheduledDays(Collections.emptyList());

        return dto;
    }

    protected WorkingSchedule transform(WorkingScheduleDTO dto)
    {
        WorkingSchedule entity = new WorkingSchedule();
        entity.setId(dto.getId());
        entity.setFromHour(dto.getFromHour());
        entity.setToHour(dto.getToHour());
        entity.setVisitDuration(dto.getVisitDuration());
        Long dayId = dto.getDay();
        if (dayId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Day id is null");
        Day day = dayService.one(dayId);
        entity.setDay(day);
        return entity;
    }

    protected WorkingScheduleDTO transform(WorkingSchedule entity)
    {
        WorkingScheduleDTO dto = new WorkingScheduleDTO();
        Day day = entity.getDay();
        if (day != null)
            dto.setDay(day.getId());
        dto.setFromHour(entity.getFromHour());
        dto.setToHour(entity.getToHour());
        dto.setVisitDuration(entity.getVisitDuration());
        dto.setId(entity.getId());
        return dto;
    }

    protected Day transform(DayDTO dto)
    {
        Day day = new Day();
        day.setDate(dto.getDate());
        Long doctorId = dto.getDoctor();
        if (doctorId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Doctor id is null");
        Doctor doctor = doctorService.one(doctorId);
        day.setDoctor(doctor);
        List<WorkingSchedule> schedules = new ArrayList<>();
        List<WorkingScheduleDTO> schedulesDTO = dto.getSchedules();
        if (!CollectionUtils.isEmpty(schedulesDTO))
        {
            schedulesDTO.forEach(
                workingSchedule -> schedules.add(transform(workingSchedule)));
            day.setSchedules(schedules);
        } else
            day.setSchedules(Collections.emptyList());

        day.setId(dto.getId());
        return day;
    }

    protected DayDTO transform(Day entity)
    {
        DayDTO dto = new DayDTO();
        dto.setDate(entity.getDate());
        dto.setId(entity.getId());
        dto.setWorkingDay(entity.isWorkingDay());
        List<WorkingSchedule> schedules = entity.getSchedules();
        if (!CollectionUtils.isEmpty(schedules))
        {
            List<WorkingScheduleDTO> schedulesDTO = new ArrayList<>();
            schedules
                    .forEach(schedule -> schedulesDTO.add(transform(schedule)));
            dto.setSchedules(schedulesDTO);
        } else
            dto.setSchedules(Collections.emptyList());
        return dto;
    }

/*    protected Attachment transform(AttachmentDTO dto)
    {
        Attachment entity = new Attachment();
        entity.setContent(dto.getContent());
        entity.setName(dto.getName());
        entity.setId(dto.getId());
        Long visitId = dto.getVisit();
        if (visitId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Visit id is null");
        Visit visit = visitService.one(visitId);
        if (visit != null)
            entity.setVisit(visit);
        return entity;
    }

    protected AttachmentDTO transform(Attachment entity)
    {
        AttachmentDTO dto = new AttachmentDTO();
        dto.setContent(entity.getContent());
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        Visit visit = entity.getVisit();
        if (visit != null)
            dto.setVisit(visit.getId());
        return dto;
    }*/
}
