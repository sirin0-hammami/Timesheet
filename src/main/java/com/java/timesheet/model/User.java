package com.java.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @Column (name="nom", nullable = false)
    private String nom;
    @Column (name="prenom", nullable = false)
    private String prenom;
    @Column (name="email", nullable = false, unique = true)
    private String email;
    @Column (name="role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany( mappedBy = "user" , cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Task> tasksOfUser = new HashSet<>();

    @JsonIgnore
    @OneToMany( mappedBy = "user" , cascade = CascadeType.ALL,orphanRemoval = true )
    private Set<Pointage> pOfUser = new HashSet<>();

    public Set<Pointage> getpOfUser() {
        return pOfUser;
    }

    public void setpOfUser(Set<Pointage> pOfUser) {
        this.pOfUser = pOfUser;
    }


    public User() {
    }

    public long getId() {
        return idUser;
    }

    public void setId(long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.singleton(new SimpleGrantedAuthority(this.getRole().toString()));}

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public Set<Task> getTasksOfUser() {
        return tasksOfUser;
    }
    public void setTasksOfUser(Set<Task> tasksOfUser) {
        this.tasksOfUser = tasksOfUser;
    }
}
