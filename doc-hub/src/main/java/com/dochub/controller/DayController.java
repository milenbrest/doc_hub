package com.dochub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.DayDTO;
import com.dochub.entity.Day;
import com.dochub.service.DayService;

@RestController
@RequestMapping("day")
public class DayController
{
    @Autowired
    private DayService  dayService;
    @Autowired
    private Transformer transformer;

    @PostMapping(value = "/add")
    public DayDTO add(@RequestBody DayDTO dayDTO)
    {
        if (dayDTO == null)
            throw new IllegalStateException("Day is null");
        Day day = transformer.transform(dayDTO);
        day = dayService.add(day);
        return transformer.transform(day);
    }
}
