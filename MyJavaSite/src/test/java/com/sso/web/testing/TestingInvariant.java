package com.sso.web.testing;

import com.sso.web.logic.Det;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingInvariant extends Det{

    @Test
    public void checkI1() throws Exception {
        double[] invariant = myDets(new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(16.5, invariant[0]);
    }

    @Test
    public void checkI2() throws Exception {
        double[] invariant = myDets(new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(32.5, invariant[1]);
    }

    @Test
    public void checkI3() throws Exception {
        double[] invariant = myDets(new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(-193.0, invariant[2]);
    }

    @Test
    public void checkK3() throws Exception {
        double[] invariant = myDets(new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(-760.0, invariant[5]);
    }

    @Test
    public void checkK2() throws Exception {
        double[] invariant = myDets(new double[] {4, -5, 0, 0, 0, 0, 0, 0, 0, -6});
        assertEquals(120.0, invariant[4]);
    }

    @Test
    public void checkK1() throws Exception {
        double[] invariant = myDets(new double[] {4, 0, 0, 0, 0, 0, 0, 0, 0, -6});
        assertEquals(-24.0, invariant[3]);
    }

}
