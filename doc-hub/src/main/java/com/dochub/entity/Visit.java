package com.dochub.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.dochub.utils.VisitHourInterval;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(uniqueConstraints =
    { @UniqueConstraint(columnNames =
                { "date", "visitHour", "patient_id" }),
      @UniqueConstraint(columnNames =
      { "date", "visitHour", "doctor_id" }) })
@Getter
@Setter
public class Visit
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long              id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor            doctor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient           patient;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date              date;
    @Column(nullable = false)
    private VisitHourInterval visitHour;
}
