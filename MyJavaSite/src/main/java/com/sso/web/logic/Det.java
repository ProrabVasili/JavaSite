package com.sso.web.logic;

import org.springframework.ui.ModelMap;

public interface Det{
    static double det(double[][] arr){
        if (arr.length == 2)
            return arr[0][0]*arr[1][1]-arr[0][1]*arr[1][0];
        else if (arr.length == 3)
            return arr[0][0]*arr[1][1]*arr[2][2]+arr[0][1]*arr[1][2]*arr[2][0]+arr[0][2]*arr[1][0]*arr[2][1]-arr[0][2]*arr[1][1]*arr[2][0]-arr[0][0]*arr[1][2]*arr[2][1]-arr[0][1]*arr[1][0]*arr[2][2];
        else {
            double[][] mas = new double[3][3];
            double sgn = 1, s = 0;
            for (int i = 0;i<4;i++){
                int im = 0;
                for (int j = 0;j<4;j++) {
                    int jm = 0;
                    for (int k = 0;k<4;k++){
                        if (0 != j && i != k) {
                            mas[im][jm] = arr[j][k];
                            jm++;
                        }
                    }
                    if (0 != j)
                        im++;
                }
                s += sgn*arr[0][i]*det(mas);
                sgn *= -1;
            }
            return s;
        }
    }

    static String det2(String[] nums) {
        return "\\(\\begin{vmatrix} " + nums[0] + " & " + nums[1] + " \\\\ " + nums[1] + " & " + nums[2] + " \\end{vmatrix} \\)";
    }

    static String det2(double[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            arr[i] = Double.toString(nums[i]);
        return det2(arr);
    }

    static String sumDet2(String[] nums) {
        return " "+det2 (new String[] {nums[0], nums[1], nums[2]}) + " + " + det2 (new String[]{nums[3], nums[4], nums[5]}) + " + " + det2 (new String[]{nums[6], nums[7], nums[8]});
    }

    static String sumDet2(double[] nums) {
        return " "+det2 (new double[] {nums[0], nums[1], nums[2]}) + " + " + det2 (new double[]{nums[3], nums[4], nums[5]}) + " + " + det2 (new double[]{nums[6], nums[7], nums[8]});
    }

    static String det3(String[] nums) {
        return "\\(\\begin{vmatrix} " + nums[0] + " & " + nums[1] + " & " + nums[2] + " \\\\ " + nums[1] + " & " + nums[3] + " & " + nums[4] + " \\\\ " + nums[2] + " & " + nums[4] + " & " + nums[5] + " \\end{vmatrix} \\)";
    }

    static String det3(double[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            arr[i] = Double.toString(nums[i]);
        return det3(arr);
    }

    static String sumDet3(String[] nums) {
        return " "+det3(new String[] {nums[0], nums[1], nums[2], nums[3], nums[4], nums[5]}) + " + " + det3(new String[] {nums[0], nums[6], nums[2], nums[7], nums[8], nums[5]}) + " + " + det3(new String[]{nums[3], nums[9], nums[4], nums[7], nums[8], nums[5]});
    }

    static String sumDet3(double[] nums) {
        return " "+det3(new double[] {nums[0], nums[1], nums[2], nums[3], nums[4], nums[5]}) + " + " + det3(new double[] {nums[0], nums[6], nums[2], nums[7], nums[8], nums[5]}) + " + " + det3(new double[]{nums[3], nums[9], nums[4], nums[7], nums[8], nums[5]});
    }

    static String det4(String[] nums) {
        return "\\(\\begin{vmatrix} " + nums[0] + " & " + nums[1] + " & " + nums[2] + " & " + nums[3] +" \\\\ " + nums[1] + " & " + nums[4] + " & " + nums[5] + " & " + nums[6] + " \\\\ " + nums[2] + " & " + nums[5] + " & " + nums[7] + " & " + nums[8] + " \\\\ " + nums[3] + " & " + nums[6] + " & " + nums[8] + " & " + nums[9] + " \\end{vmatrix} \\)";
    }

    static String det4(double[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            arr[i] = Double.toString(nums[i]);
        return det4(arr);
    }

    static void myDets(ModelMap model, double[] numbers){
        double d11 = numbers[0];
        double d22 = numbers[1];
        double d33 = numbers[2];
        double d12 = numbers[3]/2;
        double d13 = numbers[4]/2;
        double d23 = numbers[5]/2;
        double d1 = numbers[6]/2;
        double d2 = numbers[7]/2;
        double d3 = numbers[8]/2;
        double d0 = numbers[9];
        double[][] masI3 = {{d11, d12, d13}, {d12, d22, d23}, {d13, d23, d33}},
                masK3 = {{d11, d12, d13, d1}, {d12, d22, d23, d2}, {d13, d23, d33, d3}, {d1, d2, d3, d0}},
                masK21 = {{d11, d12, d1}, {d12, d22, d2}, {d1, d2, d0}},
                masK22 = {{d11, d13, d1}, {d13, d33, d3}, {d1, d3, d0}},
                masK23 = {{d22, d23, d2}, {d23, d33, d3}, {d2, d3, d0}},
                masI21 = {{d11, d12}, {d12, d22}},
                masI22 = {{d11, d13}, {d13, d33}},
                masI23 = {{d22, d23}, {d23, d33}},
                masK11 = {{d11, d1}, {d1, d0}},
                masK12 = {{d22, d2}, {d2, d0}},
                masK13 = {{d33, d3}, {d3, d0}};
        model.addAttribute("I1", d11+d22+d33);
        model.addAttribute("I2", det(masI21)+det(masI22)+det(masI23));
        model.addAttribute("I3", det(masI3));
        model.addAttribute("K3", det(masK3));
        model.addAttribute("K2", det(masK21)+det(masK22)+det(masK23));
        model.addAttribute("K1", det(masK11)+det(masK12)+det(masK13));
    }
}
