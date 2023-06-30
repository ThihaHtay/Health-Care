package com.thiha.health;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "doctor_table")
public class Doctor {

    @PrimaryKey(autoGenerate = true)
        int id;

        String name;
        String address;
        String exp;
        String phoneno;
        String fee;

    public Doctor(int id, String name, String address, String exp, String phoneno, String fee) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.exp = exp;
        this.phoneno = phoneno;
        this.fee = fee;
    }

    public Doctor(String name, String address, String exp, String phoneno, String fee) {
        this.name = name;
        this.address = address;
        this.exp = exp;
        this.phoneno = phoneno;
        this.fee = fee;
    }

    public Doctor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}

