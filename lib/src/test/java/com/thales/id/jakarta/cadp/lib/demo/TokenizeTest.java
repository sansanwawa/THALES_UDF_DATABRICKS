package com.thales.id.jakarta.cadp.lib.demo;

import com.thales.id.jakarta.cadp.lib.demo.udf.fpe.numeric.Tokenize;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;

public class TokenizeTest {
    @Test
    public void testSomeTokenizeValue(){
        Tokenize multiply = new Tokenize();
        String input = "halo";
        String result = multiply.call(input);
        String expected = "This String "+input+" will be tokenize";
        assertEquals(expected,result);
    }
}
