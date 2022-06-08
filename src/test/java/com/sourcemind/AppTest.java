package com.sourcemind;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        WebApp webApp = new WebApp();

        assertEquals("AAA", webApp.test("aaa"));
    }
}
