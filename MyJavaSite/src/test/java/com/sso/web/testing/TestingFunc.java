package com.sso.web.testing;

import org.springframework.ui.ModelMap;
import java.util.Arrays;

import static com.sso.web.logic.Butler.*;
import static com.sso.web.logic.CheckCount.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingFunc {
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
