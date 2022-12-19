package com.sso.web.testing;

import com.sso.web.logic.Det;
import com.sso.web.logic.EnumType;
import com.sso.web.logic.HideSeekType;
import com.sso.web.presentation.Result;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingType extends Det {
    ModelMap cart = new ModelMap();

    @org.junit.jupiter.api.Test
    public void firstCheckTypeSSO() throws Exception {
        Result result = new Result(new double[] {4, 5, 0, 0, 0, 0, 0, 0, 0, 0});
        result.solution(cart);
        assertEquals("Пара уявних площин, що перетинаються", cart.getAttribute("typeSSO"));
    }

    @org.junit.jupiter.api.Test
    public void secondCheckTypeSSO() throws Exception {
        Result result = new Result(new double[] {2, 5, 6, 4, 9, 6, 9, 0, 0, 0});
        result.solution(cart);
        assertEquals("Двопорожнинний гіперболоїд", cart.getAttribute("typeSSO"));
    }

    @org.junit.jupiter.api.Test
    public void thirdCheckTypeSSO() throws Exception {
        Result result = new Result(new double[] {4, -0.5, 0, 0, 0, 0, 0, 0, 0, -6});
        result.solution(cart);
        assertEquals("Гіперболічний циліндр", cart.getAttribute("typeSSO"));
    }

    @org.junit.jupiter.api.Test
    public void fourthCheckTypeSSO() throws Exception {
        double[] invariants = myDets(new double[] {4, 5, 0, 0, 0, 0, 0, 0, 0, 0});
        HideSeekType hsk = new HideSeekType(invariants);
        assertEquals(EnumType.PairImaginaryIntersectingPlanes, hsk.typeSSO());
    }

    @org.junit.jupiter.api.Test
    public void fifthCheckTypeSSO() throws Exception {
        double[] invariants = myDets(new double[] {2, 5, 6, 4, 9, 6, 9, 0, 0, 0});
        HideSeekType hsk = new HideSeekType(invariants);
        assertEquals(EnumType.TwoSheetHyperboloid, hsk.typeSSO());
    }

    @org.junit.jupiter.api.Test
    public void sixthCheckTypeSSO() throws Exception {
        double[] invariants = myDets(new double[] {4, -0.5, 0, 0, 0, 0, 0, 0, 0, -6});
        HideSeekType hsk = new HideSeekType(invariants);
        assertEquals(EnumType.HyperbolicCylinder, hsk.typeSSO());
    }
}
