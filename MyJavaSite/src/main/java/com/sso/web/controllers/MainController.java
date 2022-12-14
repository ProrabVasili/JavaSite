package com.sso.web.controllers;

import com.sso.web.repository.EquationsRepository;
import com.sso.web.service.MrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.sso.web.logic.GetCoeff;

@Controller
public class MainController {

    @Autowired
    private final MrService mrs = new MrService();

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        mrs.startSite(model);
        return "home";
    }

    @RequestMapping(value ="/submit", method = RequestMethod.POST)
    public String getInput(ModelMap model, GetCoeff aStr) {
        if (mrs.addingEquation(model, aStr) == 1)
            return "result";
        return "home";
    }

    @GetMapping("/equation/{id}")
    public String pageShower(@PathVariable(value = "id") long id, ModelMap model) {
        mrs.representSolution(model, id);
        return "result";
    }
}
