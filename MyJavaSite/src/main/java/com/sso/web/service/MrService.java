package com.sso.web.service;

import com.sso.web.logic.GetCoeff;
import com.sso.web.models.Equation;
import com.sso.web.repository.EquationsRepository;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Optional;

import static com.sso.web.logic.Butler.*;
import static com.sso.web.logic.CheckCount.*;
import static com.sso.web.logic.Result.solution;

public interface MrService {
    static void startSite(EquationsRepository repository, ModelMap model) {
        if (start) {
            repository.deleteAll();
            start = false;
        }
        Iterable<Equation> posts = repository.findAll();
        model.addAttribute("posts", posts);
        if (repository.count() != 0)
            model.addAttribute("postsLen", "Len");
        model.addAttribute("title", "Main page");

    }

    static int addingEquation(EquationsRepository repository, ModelMap model, GetCoeff aStr){
        double[] a = checkNum(aStr.gimmeNum());
        String errorNum = countErrors(a);
        if (errorNum.length() != 0 || cntZero(a) == 10) {
            model.addAttribute("Errors", "Помилка! Ви ввели " + (errorNum.length() != 0  ? "неправильно дані в: " + errorNum:"лише нулі!"));
            model.addAttribute("postsLen", "Len");
            return 0;
        }
        else {
            solution(model, a);
            addNewEquation(repository, model, a);
            return 1;
        }
    }

    static void representSolution(EquationsRepository repository, ModelMap model, long id) {
        Optional<Equation> post = repository.findById(id);
        ArrayList<Equation> res = new ArrayList<>();
        post.ifPresent(res::add);
        double[] arr = arrStringToDouble(res);
        solution(model, arr);
        model.addAttribute("button1", "btn");
    }
}
