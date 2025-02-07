package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private SalarieAideADomicileService salarieAideService;
    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/salaries")
    // Permet d'optimiser en ayant seulement un seul controller qui fait un trie sur les noms avec une valeur non obligatoire.
    public String salariesList(final ModelMap model, @RequestParam(value = "nom", required = false) final String nom, @PageableDefault(size = 5) Pageable pageable, @RequestParam(value = "page", required = false, defaultValue = "0") final Integer page) throws Exception {

        Long countSalaries = salarieAideADomicileService.countSalaries();
        model.put("countSalaries", countSalaries);
        model.put("page", page);

        List<SalarieAideADomicile> salariesToDisplay;

        if (nom != null) {
            salariesToDisplay = salarieAideADomicileService.getSalaries(nom);
            if (salariesToDisplay.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            Page<SalarieAideADomicile> salariesPage = salarieAideService.getSalaries(pageable);
            salariesToDisplay = salariesPage.getContent();
            model.put("salaries", salariesPage);
        }

        Long max = salariesToDisplay.stream()
                .mapToLong(SalarieAideADomicile::getId)
                .max()
                .orElse(0);

        Long min = salariesToDisplay.stream()
                .mapToLong(SalarieAideADomicile::getId)
                .min()
                .orElse(0);

        model.put("salaries", salariesToDisplay);
        model.put("max", max);
        model.put("min", min);
        return "list";
    }

    @GetMapping("/salaries/{id}")
    public String salaries(final ModelMap model, @PathVariable final Long id) throws Exception {
        SalarieAideADomicile aide = salarieAideADomicileService.getSalarie(id);
        Long countSalaries = salarieAideADomicileService.countSalaries();

        model.put("countSalaries", countSalaries);
        model.put("aide", aide);
        if (aide == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "detail_Salarie";
    }

    @GetMapping("/salaries/aide/new")
    public String newSalarie(final ModelMap model) {
        SalarieAideADomicile newSalarie = new SalarieAideADomicile();
        Long countSalaries = salarieAideADomicileService.countSalaries();

        model.put("countSalaries", countSalaries);
        model.put("aide", newSalarie);

        return "detail_Salarie";
    }

    @PostMapping("/salaries/aide/create")
    public String newSalarie(@ModelAttribute("aide") SalarieAideADomicile newSalarie) throws SalarieException {
        salarieAideADomicileService.creerSalarieAideADomicile(newSalarie);
        return "redirect:/salaries";
    }

    @PostMapping("/salaries/aide/update/{id}")
    public String updateSalarie(@ModelAttribute("aide") SalarieAideADomicile updateSalarie, @PathVariable final Long id) throws SalarieException {
        salarieAideADomicileService.updateSalarieAideADomicile(updateSalarie);
        return "redirect:/salaries";
    }

    @GetMapping("/salaries/aide/delete/{id}")
    public String deleteConfirmation(@PathVariable final Long id, final ModelMap model) {
        SalarieAideADomicile salarieToDelete = salarieAideADomicileService.getSalarie(id);
        Long countSalaries = salarieAideADomicileService.countSalaries();
        model.put("countSalaries", countSalaries);

        model.put("salarieToDelete", salarieToDelete);
        return "delete_Confirmation";
    }

    @PostMapping("/salaries/aide/deleteConfirmation/{id}")
    public String deleteConfirmation(@PathVariable final Long id) throws SalarieException {
        salarieAideADomicileService.deleteSalarieAideADomicile(id);
        return "redirect:/salaries";
    }

}


