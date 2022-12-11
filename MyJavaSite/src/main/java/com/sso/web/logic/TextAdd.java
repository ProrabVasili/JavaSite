package com.sso.web.logic;

import org.springframework.ui.ModelMap;

import static com.sso.web.logic.Det.*;
import static com.sso.web.logic.Butler.*;

public class TextAdd {
    public static String latexText(String text) {
        return "\\(" + text + "\\)";
    }

    public static void textK1(ModelMap model, String[] arr) {
        model.addAttribute("textK1", "\\(\\begin{vmatrix} " + arr[0] + " & " + arr[1] + " \\\\ " + arr[1] + " & " + arr[2] + " \\end{vmatrix} \\)");
    }

    public static String equation(double[] arr, String[] mults) {
        String text = arr[0] + "x²";
        for (int i = 1;i<arr.length;i++)
            text += valueEqu(arr[i], mults[i]);
        return text;
    }
    public static void allEquation(ModelMap model, double[] arr) {
        String text = "Рівняння\\,поверхні:\\\\[0.1in]" + equation(arr, mults);
        model.addAttribute("equation", latexText(text));
    }

    public static String posOrNeg(double number) {
        return (number < 0 ? "":"+") + number;
    }

    public static String valueEqu(double coeff, String mult) {
        return posOrNeg(coeff) + mult;
    }

    public static void gequation(ModelMap model, String text) {
        model.addAttribute("gequation", latexText(text));
    }

    public static String nameValue(String name, double value) {
        return name + " = " +value + ",\\,";
    }

    public static void coeff(ModelMap model, double[] arr){
        String text = "Виразимо\\,коефіцієнти:\\\\[0.1in]";
        for (int i = 0;i<arr.length-1;i++)
            text += nameValue(aLatex[i], arr[i]*((i > 2 && i < 9) ? 0.5:1.0));
        model.addAttribute("coeff", latexText(text + " a_{0} = " + arr[arr.length-1]));
    }

    public static void textI1(ModelMap model, double[] arr) {
        String text = "Підрахуймо\\,ортогональні\\,інваріанти\\,I_{1},\\,I_{2},\\,I_{3}\\,та\\,K_{3}:\\\\[0.1in]"+"I_{1} = a_{11} + a_{22} + a_{33} = " + arr[0] + posOrNeg(arr[1]) + posOrNeg(arr[2]) + " = " + model.getAttribute("I1");
        model.addAttribute("textI1", latexText(text));
    }


    public static void textI2(ModelMap model, double[] arr) {
        String text = "\\(I_{2} = \\)" + sumDet2(new String[]{"a_{11}", "a_{12}", "a_{22}", "a_{11}", "a_{13}", "a_{33}", "a_{22}", "a_{23}", "a_{33}"}) + " = " + sumDet2(new double[] {arr[0], arr[3]/2.0, arr[1], arr[0], arr[4]/2.0, arr[2], arr[1], arr[5]/2.0, arr[2]}) + " = \\(" + model.getAttribute("I2") + "\\)";
        model.addAttribute("textI2", text);
    }

    public static void textI3(ModelMap model, double[] arr) {
        String text = latexText("I_{3}\\,=\\,") + det3(new String[] {"a_{11}", "a_{12}", "a_{13}", "a_{22}", "a_{23}", "a_{33}"}) + latexText("\\,=\\,") + det3(new double[] {arr[0], arr[3] / 2.0, arr[4] / 2.0, arr[1], arr[5] / 2.0, arr[2]}) + latexText("\\,=\\,"+model.getAttribute("I3"));
        model.addAttribute("textI3", text);
    }

    public static void textK3(ModelMap model, double[] arr) {
        String text = latexText("K_{3}\\,=\\,") + det4(new String[] {"a_{11}", "a_{12}", "a_{13}", "a_{1}", "a_{22}", "a_{23}", "a_{2}", "a_{33}", "a_{3}", "a_{0}"}) + latexText("\\,=\\,") + det4(new double[] {arr[0], arr[3] / 2.0, arr[4] / 2.0, arr[6] / 2.0, arr[1], arr[5] / 2.0, arr[7] / 2.0, arr[2], arr[8] / 2.0, arr[9]}) + latexText("\\,=\\," + model.getAttribute("K3"));
        model.addAttribute("textK3", text);
    }
}
