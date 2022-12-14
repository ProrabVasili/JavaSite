package com.sso.web.logic;

import com.sso.web.models.Equation;
import com.sso.web.repository.EquationsRepository;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

public class Butler {
    private final TextAdd txtAdd = new TextAdd();
    private final String[] namesAtr = {"a11", "a22", "a33", "a12", "a13", "a23", "a1", "a2", "a3", "a0"};
    private final String[] mults = {"x²", "y²", "z²", "xy", "xz", "yz", "x", "y", "z", " = 0"};

    public String replaceSpace(String text) {
        return text.replace("\\,", " ");
    }

    public void addAtrs(ModelMap model, double[] nums) {
        for (int i = 0;i<namesAtr.length;i++)
            model.addAttribute(namesAtr[i], nums[i]);
    }

    public double convertToDouble(ModelMap model, String name) {
        return (double) model.getAttribute(name);
    }

    public String ats(double[] arr) {
        String[] s = new String[arr.length];

        for (int i = 0; i < s.length; i++)
            s[i] = String.valueOf(arr[i]);

        return String.join(",", s);
    }

    public double[] arrStringToDouble(ArrayList<Equation> res) {
        String[] arrStr = res.get(0).getCoefficients().split(",");
        double[] arr = new double[arrStr.length];
        for (int i = 0;i<arrStr.length;i++)
            arr[i] = Double.parseDouble(arrStr[i]);
        return arr;
    }

    public void addNewEquation(EquationsRepository equRepository, ModelMap model, double[] arr) {
        String type = replaceSpace((String) model.getAttribute("typeSSO"));
        Equation post = new Equation(txtAdd.equation(arr, mults), ats(arr), type);
        equRepository.save(post);
    }
}
