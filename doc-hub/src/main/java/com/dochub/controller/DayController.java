package com.dochub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.DayDTO;
import com.dochub.entity.Day;
import com.dochub.service.DayService;

@RestController
public class DayController
{
    @Autowired
    private DayService  dayService;
    @Autowired
    private Transformer transformer;

    @PostMapping(value = "/day/add")
    public DayDTO add(@RequestBody DayDTO dayDTO)
    {
        if (dayDTO == null)
            throw new IllegalStateException("Day is null");
        Day day = transformer.transform(dayDTO);
        day = dayService.add(day);
        return transformer.transform(day);
    }

    @GetMapping(value = "/day/listall")
    public List<DayDTO> listAll()
    {
        List<Day> list = dayService.listAll();
        List<DayDTO> listDTO = new ArrayList<>();
        list.forEach(day -> listDTO.add(transformer.transform(day)));
        return listDTO;
    }

    @GetMapping("/day/{id}")
    private DayDTO one(@PathVariable Long id)
    {
        DayDTO dto = transformer.transform(dayService.one(id));
        return dto;
    }

    @DeleteMapping(value = "/day/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        dayService.deleteById(id);
    }
}
