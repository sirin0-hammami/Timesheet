package com.java.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Task {
    @GeneratedValue
    @Id
    private long idTache;
    @Column(name="desciptionTache")
    private String descriptionTache;
    @Column(name="dateDebT")
    private Date dateDepT;
    @Column(name="dateFinT")
    private Date dateFinT;
    @Column (name="etat")
    private boolean etat;

    @Column (name="estimation")
    private float estimation;

    @ManyToOne()
    @JoinColumn(name="id_User_Fk")
    private User user;

    public Set<Project> getProjets() {
        return projets;
    }

    public void setProjets(Set<Project> projets) {
        this.projets = projets;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "taches", cascade = CascadeType.ALL)
    private Set<Project> projets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true , mappedBy = "task")
    private Set<Note> notes = new HashSet<>();

    public Task() {
    }

    public long getIdTache() {
        return idTache;
    }

    public void setIdTache(long idTache) {
        this.idTache = idTache;
    }

    public String getDescriptionTache() {
        return descriptionTache;
    }

    public void setDescriptionTache(String descriptionTache) {
        this.descriptionTache = descriptionTache;
    }

    public Date getDateDepT() {
        return dateDepT;
    }

    public void setDateDepT(Date dateDepT) {
        this.dateDepT = dateDepT;
    }

    public Date getDateFinT() {
        return dateFinT;
    }

    public void setDateFinT(Date dateFinT) {
        this.dateFinT = dateFinT;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEtat() {
        return etat;
    }
   public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
    public float getEstimation() {
        return estimation;
    }

    public void setEstimation(float estimation) {
        this.estimation = estimation;
    }

}
