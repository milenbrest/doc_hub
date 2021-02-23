package com.dochub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dochub.dto.VisitDTO;
import com.dochub.entity.Visit;
import com.dochub.service.VisitService;

@RestController
@RequestMapping("visit")
public class VisitController
{
    @Autowired
    private VisitService visitService;
    @Autowired
    private Transformer  transformer;

    @PostMapping(value = "/add")
    public VisitDTO add(@RequestBody VisitDTO visitDTO)
    {
        if (visitDTO == null)
            throw new IllegalStateException("Visit is null");
        Visit visit = transformer.transform(visitDTO);
        visit = visitService.add(visit);
        return transformer.transform(visit);
    }

    @GetMapping(value = "/listall")
    public List<VisitDTO> listAll(Pageable pageable)
    {
        Page<Visit> list = visitService.listAll(pageable);
        List<VisitDTO> listDTO = new ArrayList<>();
        list.forEach(visit -> listDTO.add(transformer.transform(visit)));
        return listDTO;
    }

    @GetMapping(value = "/listallbydate")
    public List<VisitDTO> listAll(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
        Pageable pageable)
    {
        List<Visit> list = visitService.listAllAfterDate(date, pageable);
        List<VisitDTO> listDTO = new ArrayList<>();
        list.forEach(visit -> listDTO.add(transformer.transform(visit)));
        return listDTO;
    }

    @GetMapping("/{id}")
    public VisitDTO one(@PathVariable Long id)
    {
        VisitDTO dto = transformer.transform(visitService.one(id));
        return dto;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        visitService.deleteById(id);
    }

}
