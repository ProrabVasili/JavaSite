package com.sso.web.controllers;

import static com.sso.web.logic.Butler.*;
import static com.sso.web.logic.CheckCount.countErrors;
import static com.sso.web.logic.CheckCount.*;
import static com.sso.web.logic.TextAdd.*;
import static com.sso.web.logic.Result.*;
import com.sso.web.models.Equation;
import com.sso.web.repository.EquationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class MainController {
    @Autowired
    private EquationsRepository equRepository;


    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        if (start) {
            equRepository.deleteAll();
            start = false;
        }
        Iterable<Equation> posts = equRepository.findAll();
        model.addAttribute("posts", posts);
        if (equRepository.count() != 0)
            model.addAttribute("postsLen", "Len");
        model.addAttribute("title", "Main page");
        return "home";
    }

    @RequestMapping(value ="/submit", method = RequestMethod.POST)
    public String getInput(ModelMap model, String a11, String a22, String a33, String a12, String a13, String a23, String a1, String a2, String a3, String a0 ) {
        String[] aStr = {a11, a22, a33, a12, a13, a23, a1, a2, a3, a0};
        double[] a = checkNum(aStr);
        String errorNum = countErrors(a);
        if (errorNum.length() != 0 || cntZero(a) == 10) {
            model.addAttribute("Errors", "Помилка! Ви ввели " + (errorNum.length() != 0  ? "неправильно дані в: " + errorNum:"лише нулі!"));
            model.addAttribute("postsLen", "Len");
            return "home";
        }
        else {
            solution(model, a);
            addNewEquation(equRepository, model, a);
            return "result";
        }
    }

    @GetMapping("/equation/{id}")
    public String pageShower(@PathVariable(value = "id") long id, ModelMap model) {
        Optional<Equation> post = equRepository.findById(id);
        ArrayList<Equation> res = new ArrayList<>();
        post.ifPresent(res::add);
        double[] arr = arrStringToDouble(res);
        solution(model, arr);
        model.addAttribute("button1", "btn");
        return "result";
    }
}
