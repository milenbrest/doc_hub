package com.dochub.dto;

import java.util.Date;

import com.dochub.utils.VisitHourInterval;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitDTO
{
    private Long              id;
    private Long              doctor;
    private Long              patient;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date              date;
    private VisitHourInterval visitHour;
}
