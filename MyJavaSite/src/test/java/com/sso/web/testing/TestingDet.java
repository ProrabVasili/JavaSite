package com.sso.web.testing;

import com.sso.web.logic.Det;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestingDet extends Det{

    @Test
    public void det2() throws Exception {
        double[][] arr = {{10, 5}, {2, 3}};
        assertEquals(20, det(arr));
    }

    @Test
    public void det3() throws Exception {
        double[][] arr = {{10, 5, 2}, {2, 3, -6}, {-10, 0, 6}};
        assertEquals(480, det(arr));
    }

    @Test
    public void det4() throws Exception {
        double[][] arr = {{10, 5, 2, -5}, {2, 3, -6, 12}, {-10, 0, 6, -9}, {45, 56, -20, 10}};
        assertEquals(-16194, det(arr));
    }
}
