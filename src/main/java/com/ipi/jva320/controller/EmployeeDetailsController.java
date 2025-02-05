package com.ipi.jva320.controller;

import com.ipi.jva320.model.SalarieAideADomicile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class EmployeeDetailsController {


    @GetMapping(value = "/salaries/1")
    public String salaries(final ModelMap model) {
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeannette Dupontelle", LocalDate.of(2021, 7, 1), LocalDate.now(), 0, 0, 10, 1, 0);

        model.put("nom", aide.getNom());
        model.put("moisDebutContrat", aide.getMoisDebutContrat());
        model.put("moisEnCours", aide.getMoisEnCours());
        model.put("joursTravaillesAnneeNMoins1", aide.getJoursTravaillesAnneeNMoins1());
        model.put("congesPayesAcquisAnneeNMoins1", aide.getCongesPayesAcquisAnneeNMoins1());
        model.put("congesPayesPrisAnneeNMoins1", aide.getCongesPayesPrisAnneeNMoins1());
        model.put("joursTravaillesAnneeN", aide.getJoursTravaillesAnneeN());
        model.put("congesPayesAcquisAnneeN", aide.getCongesPayesAcquisAnneeN());

        return "detail_Salarie";
    }
}
