package com.sso.web.testing;

import com.sso.web.logic.CheckCount;
import com.sso.web.logic.Converter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingFunc {
    @Test
    public void doubleArrToString() {
        double[] arr = {1, 4, 5};
        assertEquals("1.0,4.0,5.0", Converter.arrDoubleToString(arr));
    }

    @Test
    public void onlyDoubleNums() {
        String[] arr = {"3.5", "4", "-2.5"};
        assertEquals(Arrays.toString(new double[] {3.5, 4.0, -2.5}), Arrays.toString(CheckCount.checkNum(arr)));
    }

    @Test
    public void notAllDoubleNums() {
        String[] arr = {"Hello", "4", "-2.5"};
        assertEquals(Arrays.toString(new double[] {Double.MAX_VALUE, 4.0, -2.5}), Arrays.toString(CheckCount.checkNum(arr)));
    }

    @Test
    public void findErrors() {
        double[] arr = {Double.MAX_VALUE, 4, Double.MAX_VALUE};
        CheckCount check = new CheckCount(arr);
        assertEquals("a11, a33", check.countErrors());
    }

    @Test
    public void countZeros() {
        double[] arr = {-145, 0, Double.MAX_VALUE, 0, 0};
        CheckCount check = new CheckCount(arr);
        assertEquals(3, check.countZero());
    }

}
