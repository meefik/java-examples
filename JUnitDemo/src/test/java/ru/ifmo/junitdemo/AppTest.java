package ru.ifmo.junitdemo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Юнит-тест для getRand()
 */
public class AppTest extends Assert {

    private int n;
    private int p;
    
    @Before
    public void setVars() throws Exception {
        this.n = (int) Math.random() * 10;
        this.p = (int) Math.random() * 10;
    }

    @After
    public void clearVars() throws Exception {
        this.n = 0;
        this.p = 0;
    }

    @Test
    public void testGenRand() {
        long[] arr = App.genRand(n, p);

        assertTrue(n == arr.length);
        
        long pow = (long) Math.pow(10, p);
        for (long i : arr) {
            if ((i + "").length() > (pow + "").length()) {
                assertFalse(true);
            }
        }
        
        //assertEquals("abc", "cba"); // fail
    }

}
