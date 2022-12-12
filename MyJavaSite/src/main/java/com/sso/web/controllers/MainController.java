package com.sso.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.sso.web.logic.GetCoeff;
import com.sso.web.repository.EquationsRepository;
import static com.sso.web.service.MrService.*;

@Controller
public class MainController {
    @Autowired
    private EquationsRepository equRepository;


    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        startSite(equRepository, model);
        return "home";
    }

    @RequestMapping(value ="/submit", method = RequestMethod.POST)
    public String getInput(ModelMap model, GetCoeff aStr) {
        if (addingEquation(equRepository, model, aStr) == 1)
            return "result";
        return "home";
    }

    @GetMapping("/equation/{id}")
    public String pageShower(@PathVariable(value = "id") long id, ModelMap model) {
        representSolution(equRepository, model, id);
        return "result";
    }
}
