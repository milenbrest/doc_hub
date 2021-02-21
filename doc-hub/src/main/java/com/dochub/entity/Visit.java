package com.dochub.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dochub.utils.VisitHourInterval;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long doctor;
	private Long patient;
	private Long day;
	private Date date;
	private VisitHourInterval visitHour;
}
