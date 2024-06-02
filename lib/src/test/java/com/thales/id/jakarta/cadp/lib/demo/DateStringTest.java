package com.thales.id.jakarta.cadp.lib.demo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateStringTest {

    private static final Logger logger = LogManager.getLogger(DateStringTest.class);

    @Before
    public void setUp() {
        Configurator.setAllLevels("", Level.INFO);
    }

    @Test
    public void testSomeTokenizeValue(){
        //Data = [2023-10-25] , Result = [6363-53-47]
        String date = "6363-53-47";
        int startYear       = 1900;
        int endYear         = 2000;

        Date dateObj = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
            dateObj = formatter.parse(date);
            logger.info("date = {}" , dateObj);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e);
        }

    }
}
