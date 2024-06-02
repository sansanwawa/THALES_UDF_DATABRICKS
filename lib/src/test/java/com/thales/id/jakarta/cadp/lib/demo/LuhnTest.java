package com.thales.id.jakarta.cadp.lib.demo;

import com.thales.id.jakarta.cadp.lib.demo.service.Luhn;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.junit.Test;

public class LuhnTest {

    private static final Logger logger = LogManager.getLogger(LuhnTest.class);

    @Before
    public void setUp() {
        Configurator.setAllLevels("", Level.INFO);
    }
    @Test
    public void test(){
        String cardNo = "378282246310005";
        boolean isLuhn = Luhn.check(cardNo);
        logger.info("isLuhn = {}", isLuhn);
    }


}
