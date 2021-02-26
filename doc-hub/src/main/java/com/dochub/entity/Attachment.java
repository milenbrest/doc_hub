package com.dochub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Attachment
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;

    private String path;
    private String name;
    @ManyToOne
    private Visit  visit;

}
