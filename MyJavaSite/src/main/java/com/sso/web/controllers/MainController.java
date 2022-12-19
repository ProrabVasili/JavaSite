package com.sso.web.controllers;

import com.sso.web.presentation.PageWritter;
import com.sso.web.repository.EquationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.sso.web.logic.GetCoeff;

@Controller
public class MainController {

    @Autowired
    private EquationsRepository equRepository;

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        PageWritter.startSite(equRepository, model);
        return "home";
    }

    @RequestMapping(value ="/submit", method = RequestMethod.POST)
    public String getInput(ModelMap model, GetCoeff coeffEquation) throws Exception {
        return PageWritter.addingEquation(equRepository, model, coeffEquation);
    }

    @GetMapping("/equation/{id}")
    public String pageShower(@PathVariable(value = "id") long id, ModelMap model) throws Exception {
        PageWritter.representSolution(equRepository, id, model);
        return "result";
    }
}
