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
    private final EquationsRepository equRepository;

    public PageWritter() throws Exception {
        throw new Exception("Access denied - Repository not found");
    }

    public PageWritter(EquationsRepository repository) {
        this.equRepository = repository;
    }

    public void startSite(ModelMap model) {
        Iterable<Equation> posts = this.equRepository.findAll();
        model.addAttribute("posts", posts);
        if (this.equRepository.count() != 0)
            model.addAttribute("postsLen", "Len");
        model.addAttribute("title", "Main page");
    }

    public String addingEquation(ModelMap model, GetCoeff coeffEquation) throws Exception {
        double[] a = CheckCount.checkNum(coeffEquation.gimmeNum());
        Result result = new Result(a);
        CheckCount check = new CheckCount(a);
        String errorNum = check.countErrors();
        if (errorNum.length() != 0 || check.countZero() == 6) { // 6 - кількість коефіцієнтів (а11, а22, а33, а12, а13, а23), за яких і буде ця поверхня 2-го роду
            model.addAttribute("Errors", "Помилка! " + (errorNum.length() != 0 ? "Ви ввели неправильно дані в: " + errorNum:"Рівняння не задає поверхню другого роду!"));
            model.addAttribute("postsLen", "Len");
            return "home";
        }
        else {
            result.solution(model);
            addNewEquation(model, a);
            return "result";
        }
    }

    private void addNewEquation(ModelMap model, double[] arr) throws Exception {
        String type = replaceSpace((String) model.getAttribute("typeSSO")); //typeSSO = type Second-Order Surface
        TextAdd txtAdd = new TextAdd(arr);
        Equation post = new Equation(txtAdd.equation(), Converter.arrDoubleToString(arr), type);
        this.equRepository.save(post);
    }

    private String replaceSpace(String text) {
        return text.replace("\\,", " ");
    }
    public void representSolution(long id, ModelMap model) throws Exception {
        Optional<Equation> post = this.equRepository.findById(id);
        ArrayList<Equation> res = new ArrayList<>();
        post.ifPresent(res::add);
        double[] arr = Converter.arrStringToDouble(res);
        Result result = new Result(arr);
        result.solution(model);
        model.addAttribute("button1", "btn");
    }

}
