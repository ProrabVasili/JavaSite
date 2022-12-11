package com.sso.web.testing;

import org.springframework.ui.ModelMap;
import java.util.Arrays;

import static com.sso.web.logic.Butler.*;
import static com.sso.web.logic.CheckCount.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingFunc {
    private final double[] nums = {4, 5, 8, -8, 4, 3, 0, -1, 10, 13};
    private final String[] namesAtr = {"a11", "a22", "a33", "a12", "a13", "a23", "a1", "a2", "a3", "a0"};

    ModelMap cart = new ModelMap();

    private static String replaceLatexSpace(String text) {
        return text.replace("\\,", " ");
    }


    @org.junit.jupiter.api.Test
    public void DoubleArrToString() {
        double[] arr = {1, 4, 5};
        assertEquals("1.0,4.0,5.0", ats(arr));
    }

    @org.junit.jupiter.api.Test
    public void OnlyDoubleNums() {
        String[] arr = {"3.5", "4", "-2.5"};
        assertEquals(Arrays.toString(new double[] {3.5, 4.0, -2.5}), Arrays.toString(checkNum(arr)));
    }

    @org.junit.jupiter.api.Test
    public void NotAllDoubleNums() {
        String[] arr = {"Hello", "4", "-2.5"};
        assertEquals(Arrays.toString(new double[] {Double.MAX_VALUE, 4.0, -2.5}), Arrays.toString(checkNum(arr)));
    }

    @org.junit.jupiter.api.Test
    public void FindErrors() {
        double[] arr = {Double.MAX_VALUE, 4, Double.MAX_VALUE};
        assertEquals("a11, a33", countErrors(arr));
    }
}
