package com.dochub.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dochub.entity.Visit;

public interface VisitRepository extends PagingAndSortingRepository<Visit, Long>
{
    List<Visit> findAllByDateAfter(Pageable pageable, Date date);

}
