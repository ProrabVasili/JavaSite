package com.sso.web.testing;

import org.springframework.ui.ModelMap;

import static com.sso.web.logic.Result.solution;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingInvariant {
    ModelMap cart = new ModelMap();

    @org.junit.jupiter.api.Test
    public void CheckI1(){
        solution(cart, new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(16.5, cart.getAttribute("I1"));
    }

    @org.junit.jupiter.api.Test
    public void CheckI2(){
        solution(cart, new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(32.5, cart.getAttribute("I2"));
    }

    @org.junit.jupiter.api.Test
    public void CheckI3(){
        solution(cart, new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(-193.0, cart.getAttribute("I3"));
    }

    @org.junit.jupiter.api.Test
    public void CheckK3(){
        solution(cart, new double[] {10, -0.5, 7, -4, 8, 6, 4, 4, 0, 2});
        assertEquals(-760.0, cart.getAttribute("K3"));
    }

    @org.junit.jupiter.api.Test
    public void CheckK2(){
        solution(cart, new double[] {4, -5, 0, 0, 0, 0, 0, 0, 0, -6});
        assertEquals(120.0, cart.getAttribute("K2"));
    }

    @org.junit.jupiter.api.Test
    public void CheckK1(){
        solution(cart, new double[] {4, 0, 0, 0, 0, 0, 0, 0, 0, -6});
        assertEquals(-24.0, cart.getAttribute("K1"));
    }
}
