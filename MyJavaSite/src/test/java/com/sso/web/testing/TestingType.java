package com.sso.web.testing;

import org.springframework.ui.ModelMap;

import static com.sso.web.logic.Result.solution;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingType {
    private static String replaceLatexSpace(String text) {
        return text.replace("\\,", " ");
    }
    ModelMap cart = new ModelMap();

    @org.junit.jupiter.api.Test
    public void FirstCheckTypeSSO(){
        solution(cart, new double[] {4, 5, 0, 0, 0, 0, 0, 0, 0, 0});
        assertEquals("Пара уявних площин, що перетинаються", replaceLatexSpace((String) cart.getAttribute("typeSSO")));
    }

    @org.junit.jupiter.api.Test
    public void SecondCheckTypeSSO(){
        solution(cart, new double[] {2, 5, 6, 4, 9, 6, 9, 0, 0, 0});
        assertEquals("Двопорожнинний гіперболоїд", replaceLatexSpace((String) cart.getAttribute("typeSSO")));
    }

    @org.junit.jupiter.api.Test
    public void ThirdCheckTypeSSO(){
        solution(cart, new double[] {4, -0.5, 0, 0, 0, 0, 0, 0, 0, -6});
        assertEquals("Гіперболічний циліндр", replaceLatexSpace((String) cart.getAttribute("typeSSO")));
    }
}
