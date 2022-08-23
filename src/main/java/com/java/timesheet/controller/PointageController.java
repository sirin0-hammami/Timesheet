package com.java.timesheet.controller;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.java.timesheet.model.Pointage;
import com.java.timesheet.service.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PointageController {
    @Autowired
    private PointageService pointageService;

    @RequestMapping("/pointages")
    public List<Pointage> getAllPointage(){return pointageService.getAllPointage() ; }


    @RequestMapping(value="/pointage/date/{date}", method = RequestMethod.GET)
    public Set<Pointage> getPointageByDate(@PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return pointageService.getPointageByDate(date);
    }

    @RequestMapping(value="/pointage/user/{idUser}", method = RequestMethod.GET)
    public Set<Pointage> getPointageByUser(@PathVariable(value="idUser") Long idUser){
        return pointageService.getPointageByIdUser(idUser);
    }

    @RequestMapping(value="/pointage/user/{idUser}/date/{date}", method = RequestMethod.GET)
    public Set<Pointage> getPointageByUserAndDate(@PathVariable(value="idUser") Long idUser, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date ){
        return pointageService.getPointageByDateAndUser(idUser, date);
    }

    @RequestMapping(value="/addpointage/{idUser}", method = RequestMethod.POST)
    public void addPointage( @PathVariable("idUser") Long idUser ,@RequestBody Pointage nouvPointage){
        pointageService.addPointage(nouvPointage , idUser);
    }

    @RequestMapping(value="/delp/{idp}", method = RequestMethod.DELETE)
    public void deletep(@PathVariable("idp") Long idp){
        pointageService.deletePointage(idp);
    }

}
