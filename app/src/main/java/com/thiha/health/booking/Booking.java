package com.thiha.health.booking;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "booking_table")
public class Booking {
    @PrimaryKey(autoGenerate = true)
    int id;

    String name;
    String address;
    String exp;
    String phoneno;
    String fee;
    String bdate;
    String btime;

    public Booking(int id, String name, String address, String exp, String phoneno, String fee, String bdate, String btime) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.exp = exp;
        this.phoneno = phoneno;
        this.fee = fee;
        this.bdate = bdate;
        this.btime = btime;
    }

    public Booking(String name, String address, String exp, String phoneno, String fee, String bdate, String btime) {
        this.name = name;
        this.address = address;
        this.exp = exp;
        this.phoneno = phoneno;
        this.fee = fee;
        this.bdate = bdate;
        this.btime = btime;
    }

    public Booking() {
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

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }
}
