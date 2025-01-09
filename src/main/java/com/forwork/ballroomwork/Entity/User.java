package com.forwork.ballroomwork.Entity;

import com.forwork.ballroomwork.Addons.ClientGenderTime;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "Clients")
public class User implements ClientGenderTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "AppoidntStart")
    private Time originalTime;
    @Column(name = "Contact")
    private String contact;
    @Column(name = "Category")
    private String category;
    @Column(name = "Date")
    private java.util.Date date;
    @Column(name = "Time")
    private Time time;

    @Override
    public void AdjustTimeByGender() {
        this.originalTime = this.time;
        if ("girl".equalsIgnoreCase(gender)) {
            this.time = Time.valueOf(this.time.toLocalTime().plusMinutes(50));
        } else if ("boy".equalsIgnoreCase(gender)) {
            this.time = Time.valueOf(this.time.toLocalTime().plusMinutes(30));
        }
    }


    public User(Long id, String name, String gender, Time originalTime, String contact, String category, Date date, Time time) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.originalTime = originalTime;
        this.contact = contact;
        this.category = category;
        this.date = date;
        this.time = time;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Time getOriginalTime() {
        return originalTime;
    }

    public void setOriginalTime(Time originalTime) {
        this.originalTime = originalTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
