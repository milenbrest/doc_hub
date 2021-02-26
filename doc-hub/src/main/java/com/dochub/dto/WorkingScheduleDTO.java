package com.dochub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkingScheduleDTO
{
    private Long id;
    // in minutes
    private int  fromHour;
    // in minutes
    private int  toHour;
    private int  visitDuration;
    private Long day;
}
