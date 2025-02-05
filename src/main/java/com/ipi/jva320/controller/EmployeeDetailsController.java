package com.ipi.jva320.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeDetailsController {


    @GetMapping(value = "/salaries/1")
    public String salaries() {
        return "detail_Salarie";
    }
}
