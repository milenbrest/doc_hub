package com.dochub.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.dochub.entity.WorkingSchedule;

public interface WorkingScheduleRepository
        extends PagingAndSortingRepository<WorkingSchedule, Long>
{
    @Query("SELECT ws FROM WorkingSchedule ws "
            + "INNER JOIN ws.day AS d "
            + "INNER JOIN d.doctor AS dc "
            + "WHERE dc.id = :doctor and date(d.date) = date(:date)")
    List<WorkingSchedule> findAllByDoctorAndDate(
        @Param("doctor") Long doctor,
        @Param("date") Date date);
}
