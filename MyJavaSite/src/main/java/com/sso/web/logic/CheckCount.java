package com.sso.web.logic;

import java.util.ArrayList;


public class CheckCount {
    private final double[] coeffs;
    private final String[] namesAtr = {"a11", "a22", "a33", "a12", "a13", "a23", "a1", "a2", "a3", "a0"};

    public CheckCount() throws Exception {
        throw new Exception("Access denied - Array must be non-zero");
    }
    public CheckCount(double[] arr) {
        this.coeffs = new double[arr.length];
        System.arraycopy(arr, 0, this.coeffs, 0, arr.length);
    }
    public String countErrors() {
        ArrayList<String> errors = new ArrayList<>();
        for (int i = 0;i<this.coeffs.length;i++)
            if (this.coeffs[i] == Double.MAX_VALUE)
                errors.add(namesAtr[i]);
        return String.join(", ", errors);
    }

    public int countZero() {
        int cnt = 0, maxLen = Math.min(this.coeffs.length, 5);
        for (int i = 0;i<maxLen+1;i++)
            if (this.coeffs[i] == 0)
                cnt++;
        return cnt;
    }

    public static double[] checkNum(String[] numbers) {
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
