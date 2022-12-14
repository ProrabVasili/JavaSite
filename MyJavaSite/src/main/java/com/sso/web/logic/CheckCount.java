package com.sso.web.logic;

import java.util.ArrayList;


public class CheckCount {
    private final String[] namesAtr = {"a11", "a22", "a33", "a12", "a13", "a23", "a1", "a2", "a3", "a0"};
    public String countErrors(double[] arr) {
        ArrayList<String> errors = new ArrayList<>();
        for (int i = 0;i<arr.length;i++)
            if (arr[i] == Double.MAX_VALUE)
                errors.add(namesAtr[i]);
        return String.join(", ", errors);
    }

    public int cntZero(double[] arr) {
        int cnt = 0;
        for (double i:arr)
            if (i == 0)
                cnt++;
        return cnt;
    }

    public double[] checkNum(String[] numbers) {
        double[] dnum = new double[numbers.length];
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
