package com.sso.web.testing;

import com.sso.web.logic.Det;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingInvariant extends Det{

    @org.junit.jupiter.api.Test
    public void checkI1(){
        double[] invariant = myDets(new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(16.5, invariant[0]);
    }

    @org.junit.jupiter.api.Test
    public void checkI2(){
        double[] invariant = myDets(new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(32.5, invariant[1]);
    }

    @org.junit.jupiter.api.Test
    public void checkI3(){
        double[] invariant = myDets(new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(-193.0, invariant[2]);
    }

    @org.junit.jupiter.api.Test
    public void checkK3(){
        double[] invariant = myDets(new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(-760.0, invariant[5]);
    }

    @org.junit.jupiter.api.Test
    public void checkK2(){
        double[] invariant = myDets(new double[] {4, -5, 0, 0, 0, 0, 0, 0, 0, -6});
        assertEquals(120.0, invariant[4]);
    }

    @org.junit.jupiter.api.Test
    public void checkK1(){
        double[] invariant = myDets(new double[] {4, 0, 0, 0, 0, 0, 0, 0, 0, -6});
        assertEquals(-24.0, invariant[3]);
    }
}
