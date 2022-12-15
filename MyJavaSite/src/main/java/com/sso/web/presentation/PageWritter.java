package com.sso.web.presentation;

import com.sso.web.logic.CheckCount;
import com.sso.web.logic.Converter;
import com.sso.web.logic.GetCoeff;
import com.sso.web.logic.TextAdd;
import com.sso.web.models.Equation;
import com.sso.web.repository.EquationsRepository;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Optional;

public class PageWritter {
    public static void startSite(EquationsRepository equRepository, ModelMap model) {
        Iterable<Equation> posts = equRepository.findAll();
        model.addAttribute("posts", posts);
        if (equRepository.count() != 0)
            model.addAttribute("postsLen", "Len");
        model.addAttribute("title", "Main page");
    }

    public static String addingEquation(EquationsRepository equRepository, ModelMap model, GetCoeff coeffEquation){
        double[] a = CheckCount.checkNum(coeffEquation.gimmeNum());
        Result result = new Result(a);
        CheckCount check = new CheckCount(a);
        String errorNum = check.countErrors();
        if (errorNum.length() != 0 || check.countZero() == 10) { // 10 - кількість змінних, якi використовуються у рівнянні
            model.addAttribute("Errors", "Помилка! Ви ввели " + (errorNum.length() != 0  ? "неправильно дані в: " + errorNum:"лише нулі!"));
            model.addAttribute("postsLen", "Len");
            return "home";
        }
        else {
            result.solution(model);
            addNewEquation(equRepository, model, a);
            return "result";
        }
    }

    private static void addNewEquation(EquationsRepository equRepository, ModelMap model, double[] arr) {
        String type = replaceSpace((String) model.getAttribute("typeSSO")); //typeSSO = type Second-Order Surface
        TextAdd txtAdd = new TextAdd(arr);
        Equation post = new Equation(txtAdd.equation(), Converter.arrDoubleToString(arr), type);
        equRepository.save(post);
    }

    public static String replaceSpace(String text) {
        return text.replace("\\,", " ");
    }
    public static void representSolution(EquationsRepository equRepository, long id, ModelMap model) {
        Optional<Equation> post = equRepository.findById(id);
        ArrayList<Equation> res = new ArrayList<>();
        post.ifPresent(res::add);
        double[] arr = Converter.arrStringToDouble(res);
        Result result = new Result(arr);
        result.solution(model);
        model.addAttribute("button1", "btn");
    }

}
