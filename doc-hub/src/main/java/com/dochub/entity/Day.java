package com.dochub.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames =
    { "date", "doctor_id" }))
public class Day
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long                  id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date                  date;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor                doctor;
    @OneToMany(targetEntity = WorkingSchedule.class, mappedBy = "day")
    private List<WorkingSchedule> schedules;

    public boolean isWorkingDay()
    {
        return !CollectionUtils.isEmpty(schedules);
    }

}
