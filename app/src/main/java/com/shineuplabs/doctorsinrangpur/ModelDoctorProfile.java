package com.shineuplabs.doctorsinrangpur;

import java.util.ArrayList;

public class ModelDoctorProfile {

    String card, chamber, contact, day, edu, job, name, serial, sp, time;


    public ModelDoctorProfile() {
    }

    public ModelDoctorProfile(String card, String chamber, String contact, String day, String edu, String job, String name, String serial, String sp, String time) {
        this.card = card;
        this.chamber = chamber;
        this.contact = contact;
        this.day = day;
        this.edu = edu;
        this.job = job;
        this.name = name;
        this.serial = serial;
        this.sp = sp;
        this.time = time;
    }

    public String getCard() {
        return card;
    }

    public String getChamber() {
        return chamber;
    }

    public String getContact() {
        return contact;
    }

    public String getDay() {
        return day;
    }

    public String getEdu() {
        return edu;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getSerial() {
        return serial;
    }

    public String getSp() {
        return sp;
    }

    public String getTime() {
        return time;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
