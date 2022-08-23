package com.java.timesheet.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Pointage {
    @GeneratedValue
    @Id
    private long Pid;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column (name="date")
    private Date date;
    @Column (name="tempsArriv")
    private Time tempsArriv;
    @Column (name="tempsDep")
    private Time tempsDep;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User user;

    public Pointage() { }

    public long getPid() {
        return Pid;
    }

    public void setPid(long pid) {
        Pid = pid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTempsArriv() {
        return tempsArriv;
    }

    public void setTempsArriv(Time tempsArriv) {
        this.tempsArriv = tempsArriv;
    }

    public Time getTempsDep() {
        return tempsDep;
    }

    public void setTempsDep(Time tempsDep) {
        this.tempsDep = tempsDep;
    }
}
