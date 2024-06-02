package com.thales.id.jakarta.cadp.lib.demo;

import com.thales.id.jakarta.cadp.lib.demo.udf.fpe.alphanumeric.Tokenize;
import com.thales.id.jakarta.cadp.lib.demo.udf.fpe.alphanumeric.DeTokenize;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.spark.sql.api.java.UDF1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UDFAlphaNumTest {

    private static final Logger logger = LogManager.getLogger(UDFAlphaNumTest.class);

    @BeforeEach
    public void setUp() {
        Configurator.setAllLevels("", Level.INFO);
    }

    @Test
    public void UDFToken() throws Exception {
        UDF1 tokenize = new Tokenize();
        String token = (String) tokenize.call("sandy");
        Assertions.assertEquals("xhemk",token);

    }


    @Test
    public void UDFDeToken() throws Exception {
        UDF1 tokenize = new DeTokenize();
        String token = (String) tokenize.call("xhemk");
        Assertions.assertEquals("sandy",token);

    }

    @Test
    public void UDFTokenDetoken() throws Exception {
        String data  = "sandy";
        UDF1 tokenize = new Tokenize();
        String token = (String) tokenize.call(data);

        UDF1 detokenize = new DeTokenize();
        String detoken = (String) detokenize.call(token);
        logger.info("Data = {}, Token = {} , Detoken = {}",data,  token, detoken);
    }


}
