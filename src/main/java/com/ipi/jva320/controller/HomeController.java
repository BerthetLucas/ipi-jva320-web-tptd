package com.ipi.jva320.controller;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HomeController {
    private final SalarieAideADomicileService salarieAideADomicileService;

    public HomeController(SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    @GetMapping(value = "/")
    public String home(final ModelMap model) {
        Long countSalaries = salarieAideADomicileService.countSalaries();
        model.put("countSalaries", countSalaries);
        return "home";
    }
}
