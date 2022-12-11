package com.sso.web.logic;

import org.springframework.ui.ModelMap;

import static com.sso.web.logic.Butler.*;

public class HideSeekType {
    public static void setType(ModelMap model, int num) {
        model.addAttribute("numberOfType", num);
    }

    public static void typeSSO(ModelMap model) {
        double  I1 = convertToDouble(model, "I1"),
                I2 = convertToDouble(model, "I2"),
                I3 = convertToDouble(model, "I3"),
                K1 = convertToDouble(model, "K1"),
                K2 = convertToDouble(model, "K2"),
                K3 = convertToDouble(model, "K3");
        int num = -1;

        if (I3 != 0) {
            if (I2 > 0 && I1*I3 > 0)
                num = ellipticalType(K3);
            else if (I2 <= 0 || I1*I3 <= 0)
                num = hyperbolicType(K3);
        }
        else
            num = parabolicType(I1, I2, K1, K2, K3);
        setType(model, num);
    }

    private static int ellipticalType(double K3) {
        if (K3 < 0)
            return 1;
        else if (K3 > 0)
            return 2;
        else
            return 3;
    }

    private static int hyperbolicType(double K3) {
        if (K3 > 0)
            return 4;
        else if (K3 < 0)
            return 5;
        else
            return 6;
    }

    private static int parabolicType(double I1, double I2, double K1, double K2, double K3) {
        if (K3 < 0)
            return 7;
        else if (K3 > 0)
            return 8;
        else {
            if (I2 > 0)
                return I2GreaterZero(I1, K2);
            else if (I2 < 0)
                return I2LessZero(K2);
            else {
                if (K2 == 0)
                    return K2Zero(K1);
                else
                    return 17;
            }
        }
    }

    private static int I2GreaterZero(double I1, double K2) {
        if (I1*K2 < 0)
            return 9;
        else if (I1*K2 > 0)
            return 10;
        else if (K2 == 0)
            return 11;
        return -1;
    }

    private static int I2LessZero(double K2) {
        if (K2 != 0)
            return 12;
        else
            return 13;
    }

    private static int K2Zero(double K1) {
        if (K1 < 0)
            return 14;
        else if (K1 > 0)
            return 15;
        else
            return 16;
    }
}
