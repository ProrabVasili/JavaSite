package com.sso.web.service;

import com.sso.web.logic.*;
import com.sso.web.models.Equation;
import com.sso.web.repository.EquationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MrService {
    @Autowired
    private EquationsRepository equRepository;

    private final Butler butler = new Butler();
    private final Result result = new Result();
    private final CheckCount check = new CheckCount();
    private boolean start = true;

    public void startSite(ModelMap model) {
        if (start) {
            equRepository.deleteAll();
            start = false;
        }
        Iterable<Equation> posts = equRepository.findAll();
        model.addAttribute("posts", posts);
        if (equRepository.count() != 0)
            model.addAttribute("postsLen", "Len");
        model.addAttribute("title", "Main page");

    }

    public int addingEquation(ModelMap model, GetCoeff aStr){
        double[] a = check.checkNum(aStr.gimmeNum());
        String errorNum = check.countErrors(a);
        if (errorNum.length() != 0 || check.cntZero(a) == 10) {
            model.addAttribute("Errors", "Помилка! Ви ввели " + (errorNum.length() != 0  ? "неправильно дані в: " + errorNum:"лише нулі!"));
            model.addAttribute("postsLen", "Len");
            return 0;
        }
        else {
            result.solution(model, a);
            butler.addNewEquation(equRepository, model, a);
            return 1;
        }
    }

    public void representSolution(ModelMap model, long id) {
        Optional<Equation> post = equRepository.findById(id);
        ArrayList<Equation> res = new ArrayList<>();
        post.ifPresent(res::add);
        double[] arr = butler.arrStringToDouble(res);
        result.solution(model, arr);
        model.addAttribute("button1", "btn");
    }
}
