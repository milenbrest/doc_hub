package com.dochub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.WorkingScheduleDTO;
import com.dochub.entity.WorkingSchedule;
import com.dochub.service.WorkingScheduleService;

@RequestMapping("schedule")
@RestController
public class WorkingScheduleController
{
    @Autowired
    private WorkingScheduleService workingScheduleService;
    @Autowired
    private Transformer            transformer;

    @PostMapping(value = "/add")
    public WorkingScheduleDTO add(
        @RequestBody WorkingScheduleDTO workingScheduleDTO)
    {
        if (workingScheduleDTO == null)
            throw new IllegalStateException("WorkingSchedule is null");
        WorkingSchedule workingSchedule = transformer
                .transform(workingScheduleDTO);
        workingSchedule = workingScheduleService.add(workingSchedule);
        return transformer.transform(workingSchedule);
    }

}
