package com.dochub.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO
{
    private Long         id;
    private String       firstName;
    private String       lastName;
    private String       email;
    private String       phone;
    private String       education;
    private String       qualification;
    private String       info;
    /*
     * private List<DayOfWeek> notWorkingDays; private VisitHourInterval
     * workingFrom; private VisitHourInterval workingTo;
     */
    private List<DayDTO> scheduledDays;
}
