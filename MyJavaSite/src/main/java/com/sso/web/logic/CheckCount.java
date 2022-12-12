package com.sso.web.logic;

import java.util.ArrayList;

import static com.sso.web.logic.Butler.*;

public interface CheckCount {
    static String countErrors(double[] arr) {
        ArrayList<String> errors = new ArrayList<>();
        for (int i = 0;i<arr.length;i++)
            if (arr[i] == Double.MAX_VALUE)
                errors.add(namesAtr[i]);
        return String.join(", ", errors);
    }

    static int cntZero(double[] arr) {
        int cnt = 0;
        for (double i:arr)
            if (i == 0)
                cnt++;
        return cnt;
    }

    static double[] checkNum(String[] numbers) {
        double[] dnum = new double[numbers.length];
        double num;
        for (int i = 0;i<numbers.length;i++){
            try {
                dnum[i] = Double.parseDouble(numbers[i]);
            }
            catch (NumberFormatException ee){
                dnum[i] = Double.MAX_VALUE;
            }
        }
        return dnum;
    }
}
