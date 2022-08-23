package com.java.timesheet.service;
import java.util.*;
import com.java.timesheet.exception.ResourceNotFoundException;
import com.java.timesheet.model.Pointage;
import com.java.timesheet.model.User;
import com.java.timesheet.repository.PointageRepository;
import com.java.timesheet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointageService {
    @Autowired
    private PointageRepository pointageRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Pointage> getAllPointage()
    {
        List<Pointage> pointages = new ArrayList<>();
        pointageRepository.findAll().forEach(pointages::add);
        return pointages;
    }


    public Set<Pointage> getPointageByDate(Date date)
    {
        Set<Pointage> pointages = pointageRepository.findByDate(date);
        return pointages;
    }

    public Set<Pointage> getPointageByIdUser(Long idUser)
    {
        User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("User not exist"));
        Set<Pointage> pointages = user.getpOfUser();
        return pointages;
    }

    public Set<Pointage> getPointageByDateAndUser(Long idUser , Date date)
    {
        User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("User not exist"));
        Set<Pointage> puser = getPointageByIdUser(idUser);
        Set<Pointage> pdate = getPointageByDate(date);
        pdate.retainAll(puser);
        return pdate;
    }

    public void addPointage(Pointage pointage , Long idUser)
    {
        User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("User not exist"));
        pointage.setUser(user);
        pointageRepository.save(pointage);
    }

    public void deletePointage( Long idp){
        Pointage p = pointageRepository.findById(idp).orElseThrow(() -> new ResourceNotFoundException("User not exist"));
        pointageRepository.delete(p);
    }
}
