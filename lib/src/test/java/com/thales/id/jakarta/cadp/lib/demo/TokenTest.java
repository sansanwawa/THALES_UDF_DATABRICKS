package com.thales.id.jakarta.cadp.lib.demo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;

public class TokenTest {

    @Before
    public void setUp() {
        Configurator.setAllLevels("", Level.INFO);
    }



}
