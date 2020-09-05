package com.shineuplabs.doctorsinrangpur;

public class ModelDoctorList {

    String name, chamber, sp;

    public ModelDoctorList() {
    }

    public ModelDoctorList(String name, String chamber, String sp) {
        this.name = name;
        this.chamber = chamber;
        this.sp = sp;
    }

    public String getName() {
        return name;
    }

    public String getChamber() {
        return chamber;
    }

    public String getSp() {
        return sp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }
}
