package com.thales.id.jakarta.cadp.lib.demo;

import com.thales.id.jakarta.cadp.lib.demo.udf.fpe.numeric.DeTokenize;
import com.thales.id.jakarta.cadp.lib.demo.udf.fpe.numeric.Tokenize;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.spark.sql.api.java.UDF1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UDFNumericTest {

    private static final Logger logger = LogManager.getLogger(UDFNumericTest.class);

    @BeforeEach
    public void setUp() {
        Configurator.setAllLevels("", Level.INFO);
    }

    @Test
    public void UDFToken() throws Exception {
        UDF1 tokenize = new Tokenize();
        String token = (String) tokenize.call("12345");
        Assertions.assertEquals("35830",token);
    }


    @Test
    public void UDFDeToken() throws Exception {
        UDF1 deTokenize = new DeTokenize();
        String deToken = (String) deTokenize.call("35830");
        Assertions.assertEquals("12345",deToken);
    }


}
