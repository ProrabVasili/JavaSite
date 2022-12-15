package com.sso.web.logic;

import com.sso.web.models.Equation;

import java.util.ArrayList;

public class Converter {

    public static String arrDoubleToString(double[] arr) {
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
}
