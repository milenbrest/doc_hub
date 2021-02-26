package com.dochub.entity;

import java.time.DayOfWeek;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.dochub.utils.VisitHourInterval;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Doctor
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long              id;
    @Column(name = "first_name")
    @NotEmpty(message = "First Name may not be empty")
    private String            firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last Name may not be empty")
    private String            lastName;
    @NotEmpty(message = "Email may not be empty")
    private String            email;
    @NotEmpty(message = "Phone may not be empty")
    private String            phone;
    private String            education;
    private String            qualification;
    private String            info;
/*    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek>   notWorkingDays;
    private VisitHourInterval workingFrom;
    private VisitHourInterval workingTo;*/
    @OneToMany(targetEntity = Day.class, mappedBy = "doctor")
    private List<Day>         scheduledDays;
    @OneToMany(targetEntity = Visit.class, mappedBy = "doctor")
    private List<Visit>       visits;
    @OneToMany(targetEntity = Rating.class, mappedBy = "doctor")
    private List<Rating>      ratings;
}
