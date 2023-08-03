package com.thiha.health.hospital;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hospital_table")
public class Hospital {

    @PrimaryKey(autoGenerate = true)
    int id;
    String hName;
    String hAddress;
    String hPhoneno;

    public Hospital(int id, String hName, String hAddress, String hPhoneno) {
        this.id = id;
        this.hName = hName;
        this.hAddress = hAddress;
        this.hPhoneno = hPhoneno;
    }

    public Hospital(String hName, String hAddress, String hPhoneno) {
        this.hName = hName;
        this.hAddress = hAddress;
        this.hPhoneno = hPhoneno;
    }

    public Hospital() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethAddress() {
        return hAddress;
    }

    public void sethAddress(String hAddress) {
        this.hAddress = hAddress;
    }

    public String gethPhoneno() {
        return hPhoneno;
    }

    public void sethPhoneno(String hPhoneno) {
        this.hPhoneno = hPhoneno;
    }
}
