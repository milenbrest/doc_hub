package com.dochub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkingSchedule
{
    // TODO: dali intervala from to se zastupva s drug
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // in minutes
    private int  fromHour;
    // in minutes
    private int  toHour;
    private int  visitDuration;
    @ManyToOne
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    private Day  day;
}
