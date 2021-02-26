package com.dochub.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long         id;
    @Column(name = "first_name")
    @NotEmpty(message = "First Name may not be empty")
    private String       firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last Name may not be empty")
    private String       lastName;
    @NotEmpty(message = "Email may not be empty")
    private String       email;
    @NotEmpty(message = "Phone may not be empty")
    private String       phone;
    @OneToMany(targetEntity = Visit.class, mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Visit>  visits;
    @OneToMany(targetEntity = Rating.class, mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Rating> ratings;
}
