package com.dochub.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayDTO
{
    private Long                     id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date                     date;
    private Long                     doctor;
    private List<WorkingScheduleDTO> schedules;
    private boolean                  workingDay;
}
