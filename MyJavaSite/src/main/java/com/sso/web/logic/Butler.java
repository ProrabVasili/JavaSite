package com.sso.web.logic;

import com.sso.web.models.Equation;
import com.sso.web.repository.EquationsRepository;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

import static com.sso.web.logic.TextAdd.equation;

public class Butler {
    public static boolean start = true;
    public static String[] namesAtr = {"a11", "a22", "a33", "a12", "a13", "a23", "a1", "a2", "a3", "a0"};
    public static String[] mults = {"x²", "y²", "z²", "xy", "xz", "yz", "x", "y", "z", " = 0"};
    public static String[] aLatex = {"a_{11}", "a_{22}", "a_{33}", "a_{12}",  "a_{13}", "a_{23}", "a_{1}", "a_{2}", "a_{3}", "a_{0}"};

    public static String replaceSpace(String text) {
        return text.replace("\\,", " ");
    }

    public static void addAtrs(ModelMap model, double[] nums) {
        for (int i = 0;i<namesAtr.length;i++)
            model.addAttribute(namesAtr[i], nums[i]);
    }

    public static double convertToDouble(ModelMap model, String name) {
        return (double) model.getAttribute(name);
    }

    public static String ats(double[] arr) {
        String[] s = new String[arr.length];

        for (int i = 0; i < s.length; i++)
            s[i] = String.valueOf(arr[i]);

        return String.join(",", s);
    }

    public static double[] arrStringToDouble(ArrayList<Equation> res) {
        String[] arrStr = res.get(0).getCoefficients().split(",");
        double[] arr = new double[arrStr.length];
        for (int i = 0;i<arrStr.length;i++)
            arr[i] = Double.parseDouble(arrStr[i]);
        return arr;
    }

    public static void addNewEquation(EquationsRepository equRepository, ModelMap model, double[] arr) {
        String type = replaceSpace((String) model.getAttribute("typeSSO"));
        Equation post = new Equation(equation(arr, mults), ats(arr), type);
        equRepository.save(post);
    }
}
