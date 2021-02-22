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

import com.dochub.dto.VisitDTO;
import com.dochub.entity.Visit;
import com.dochub.service.VisitService;

@RestController
public class VisitController
{
    @Autowired
    private VisitService visitService;
    @Autowired
    private Transformer  transformer;

    @PostMapping(value = "/visit/add")
    public VisitDTO add(@RequestBody VisitDTO visitDTO)
    {
        if (visitDTO == null)
            throw new IllegalStateException("Visit is null");
        Visit visit = transformer.transform(visitDTO);
        visit = visitService.add(visit);
        return transformer.transform(visit);
    }

    @GetMapping(value = "/visit/listall")
    public List<VisitDTO> listAll()
    {
        List<Visit> list = visitService.listAll();
        List<VisitDTO> listDTO = new ArrayList<>();
        list.forEach(visit -> listDTO.add(transformer.transform(visit)));
        return listDTO;
    }

    @GetMapping("/visit/{id}")
    private VisitDTO one(@PathVariable Long id)
    {
        VisitDTO dto = transformer.transform(visitService.one(id));
        return dto;
    }

    @DeleteMapping(value = "/visit/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        visitService.deleteById(id);
    }

}
