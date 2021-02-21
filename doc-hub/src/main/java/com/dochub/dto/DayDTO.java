package com.dochub.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayDTO {
	private Long id;
	private boolean workingDay;
	private Date date;
	private List<VisitDTO> visits;
	private Long doctor;
}
