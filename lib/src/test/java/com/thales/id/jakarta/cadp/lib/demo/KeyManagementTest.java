package com.thales.id.jakarta.cadp.lib.demo;

import com.thales.id.jakarta.cadp.lib.demo.utils.CryptoUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.junit.Test;

/**
 * Author :
 * sandy.haryono@thalesgroup.com
 */


public class KeyManagementTest {

    @Before
    public void setUp() {
        Configurator.setAllLevels("", Level.INFO);
    }
    @Test
    public void testWrappingKey(){
        CryptoUtils.init();
        CryptoUtils.wrappingKey();
    }

    @Test
    public void testImportKey(){
        CryptoUtils.init();
        CryptoUtils.importKey();
    }



}
