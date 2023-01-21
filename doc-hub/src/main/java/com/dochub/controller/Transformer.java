package com.dochub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.dochub.dto.DoctorDTO;
import com.dochub.dto.PatientDTO;
import com.dochub.dto.RatingDTO;
import com.dochub.dto.VisitDTO;
import com.dochub.entity.Doctor;
import com.dochub.entity.Patient;
import com.dochub.entity.Rating;
import com.dochub.entity.Visit;
import com.dochub.service.DoctorService;
import com.dochub.service.PatientService;

@Component
public class Transformer {
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	protected Visit transform(VisitDTO dto) {
		Visit visit = new Visit();
		Long doctorId = dto.getDoctor();
		if (doctorId == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor id cannot be null");
		Doctor doctor = doctorService.one(doctorId);
		visit.setDoctor(doctor);
		visit.setId(dto.getId());
		Long patientId = dto.getPatient();
		if (patientId == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient id cannot be null");
		Patient patient = patientService.one(patientId);
		visit.setPatient(patient);
		visit.setVisitHour(dto.getVisitHour());
		visit.setDate(dto.getDate());
		return visit;
	}

	protected VisitDTO transform(Visit visit) {
		VisitDTO//
		dto = new VisitDTO();
		if (visit.getDoctor() != null)
			dto.setDoctor(visit.getDoctor().getId());

		dto.setId(visit.getId());
		if (visit.getPatient() != null)
			dto.setPatient(visit.getPatient().getId());

		if (visit.getVisitHour() != null)
			dto.setVisitHour(visit.getVisitHour());

		dto.setDate(visit.getDate());
		return dto;
	}

	protected Rating transform(RatingDTO dto) {
		Rating rating = new Rating();
		rating.setComment(dto.getComment());
		Long doctorId = dto.getDoctor();
		if (doctorId == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor id cannot be null");
		Doctor doctor = doctorService.one(doctorId);
		rating.setDoctor(doctor);
		rating.setId(dto.getId());
		Long patientId = dto.getPatient();
		if (patientId == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient id cannot be null");
		Patient patient = patientService.one(patientId);
		rating.setPatient(patient);
		rating.setRate(dto.getRate());
		return rating;
	}

	protected RatingDTO transform(Rating rating) {
		RatingDTO dto = new RatingDTO();
		if (rating.getComment() != null)
			dto.setComment(rating.getComment());
		if (rating.getDoctor() != null)
			dto.setDoctor(rating.getDoctor().getId());
		dto.setId(rating.getId());
		if (rating.getPatient() != null)
			dto.setPatient(rating.getPatient().getId());
		dto.setRate(rating.getRate());
		return dto;
	}

	protected Patient transform(PatientDTO dto) {
		Patient patient = new Patient();
		patient.setEmail(dto.getEmail());
		patient.setFirstName(dto.getFirstName());
		patient.setId(dto.getId());
		patient.setLastName(dto.getLastName());
		patient.setPhone(dto.getPhone());
		return patient;
	}

	protected PatientDTO transform(Patient patient) {
		PatientDTO dto = new PatientDTO();
		dto.setEmail(patient.getEmail());
		dto.setFirstName(patient.getFirstName());
		dto.setId(patient.getId());
		dto.setLastName(patient.getLastName());
		dto.setPhone(patient.getPhone());
		return dto;
	}

	protected Doctor transform(DoctorDTO doctorDTO) {
		Doctor doctor = new Doctor();
		doctor.setEducation(doctorDTO.getEducation());
		doctor.setEmail(doctorDTO.getEmail());
		doctor.setFirstName(doctorDTO.getFirstName());
		doctor.setId(doctorDTO.getId());
		doctor.setInfo(doctorDTO.getInfo());
		doctor.setLastName(doctorDTO.getLastName());
		doctor.setPhone(doctorDTO.getPhone());
		doctor.setQualification(doctorDTO.getQualification());
		doctor.setNotWorkingDays(doctorDTO.getNotWorkingDays());
		doctor.setWorkingFrom(doctorDTO.getWorkingFrom());
		doctor.setWorkingTo(doctorDTO.getWorkingTo());
		return doctor;
	}

	protected DoctorDTO transform(Doctor doctor) {
		DoctorDTO dto = new DoctorDTO();
		dto.setEducation(doctor.getEducation());
		dto.setEmail(doctor.getEmail());
		dto.setFirstName(doctor.getFirstName());
		dto.setId(doctor.getId());
		dto.setInfo(doctor.getInfo());
		dto.setLastName(doctor.getLastName());
		dto.setPhone(doctor.getPhone());
		dto.setQualification(doctor.getQualification());
		dto.setNotWorkingDays(doctor.getNotWorkingDays());
		dto.setWorkingFrom(doctor.getWorkingFrom());
		dto.setWorkingTo(doctor.getWorkingTo());
		return dto;
	}
}
