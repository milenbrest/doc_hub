package com.dochub.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
    private Long             id;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor           doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient          patient;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date             date;
    // in minutes
    @Column(nullable = false)
    private int              visitHour;
    @OneToMany(targetEntity = Attachment.class, mappedBy = "visit")
    private List<Attachment> attachments;

    public Date getFullDate()
    {
        int hour = visitHour / 60;
        int minutes = (visitHour % 60) * 6;
        Calendar visitCalendar = Calendar.getInstance();
        visitCalendar.setTime(date);
        visitCalendar.set(Calendar.HOUR_OF_DAY, hour);
        visitCalendar.set(Calendar.MINUTE, minutes);
        visitCalendar.set(Calendar.MILLISECOND, 0);
        return visitCalendar.getTime();
    }
}
