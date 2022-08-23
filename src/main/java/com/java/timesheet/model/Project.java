package com.java.timesheet.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Project {
    @GeneratedValue
    @Id
    private long idProjet;
    @Column(name="nomProjet")
    private String nomProjet;
    @Column(name="dateDebP")
    private Date dateDepP;
    @Column(name="dateFinP")
    private Date dateFinP;
    @Column (name="statut")
    private boolean statut;

@ManyToMany
@JoinTable( name ="Project_task", joinColumns = @JoinColumn(name="idTache"), inverseJoinColumns = @JoinColumn(name="idProjet"))
private Set<Task> taches = new HashSet<>();


    public long getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(long idProjet) {
        this.idProjet = idProjet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public Date getDateDepP() {
        return dateDepP;
    }

    public void setDateDepP(Date dateDepP) {
        this.dateDepP = dateDepP;
    }

    public Date getDateFinP() {
        return dateFinP;
    }

    public void setDateFinP(Date dateFinP) {
        this.dateFinP = dateFinP;
    }

    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public Set<Task> getTaches() {
        return taches;
    }

    public void setTaches(Set<Task> taches) {
        this.taches = taches;
    }

}
