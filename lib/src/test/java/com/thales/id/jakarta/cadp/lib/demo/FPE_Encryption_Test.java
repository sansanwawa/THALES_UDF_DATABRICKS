package com.thales.id.jakarta.cadp.lib.demo;

import com.ingrian.security.nae.FPECharset;
import com.thales.id.jakarta.cadp.lib.demo.fpe.FPE;
import com.thales.id.jakarta.cadp.lib.demo.utils.DateUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FPE_Encryption_Test {

    private static final Logger logger = LogManager.getLogger(FPE_Encryption_Test.class);

    @Before
    public void setUp() {
        Configurator.setAllLevels("", Level.INFO);
    }


    @Test
    public void dateDiffTest() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        try {
            Date start = sdf.parse("01-01-1753");
            Date end = sdf.parse("14-05-1810");
            String diff = DateUtils.diffWithPad(start,end,6);
            logger.info("diff ==== [{}]",diff);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Date format experimental for CADP and CT-VL
     */
    @Test
    public void testCADPDateEncryption() {

        FPE ff1 = new FPE("CtsKey");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String startDate     = "01-01-1753"; //base date
        String data          = "25-10-1982";

        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(data);
            String diff = DateUtils.diffWithPad(start,end,6);
            String encryptedResult = ff1.encrypt(diff);
            Date res = DateUtils.modify(start, Integer.parseInt(encryptedResult));
            String tokenResult = sdf.format(res);

            String decryptedResult = ff1.decrypt(encryptedResult);
            Date dRes = DateUtils.modify(start, Integer.parseInt(decryptedResult));

            logger.info("Input Date     : [{}]", data);
            logger.info("Start - End    : [{}] - [{}]", startDate,data);
            logger.info("Difference     : [{}]", diff);
            logger.info("token Result   : [{}]", encryptedResult);
            logger.info("deToken Result : [{}]", decryptedResult);
            logger.info("Date Token     : [{}]", tokenResult);
            logger.info("Date Plain     : [{}]", sdf.format(dRes));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDate() {
        Date start = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            String startDate     = "01-01-1753"; //base date
            start = sdf.parse(startDate);

            Date dRes = DateUtils.modify(start, 999999);
            String dateString = sdf.format(dRes);
            logger.info("Date Min Max = {} - {}",startDate, dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }



        @Test
    public void testEncryptDecrypt() {
        FPE fpe = new FPE();
        String data = "2023-10-25";
        String encryptedResult = fpe.encrypt(data);
        String decryptedResult = fpe.decrypt(encryptedResult);

        assertEquals("6363-53-47", encryptedResult);
        assertEquals("2023-10-25", decryptedResult);

        /*
        logger.info("data = [{}], encryptedResult = [{}], decryptedResult = [{}]",
                data,
                encryptedResult,
                decryptedResult);
         */

    }


    @Test
    public void testEncryptInLoop() {
        FPE fpe = new FPE(Algo.FF1_CARD10, FPECharset.CARD10);
        for (int i=0;i<10;i++){
            String date = "2023-10-25";
            String theDate = DateUtils.modify(date,i);
            assert theDate != null;
            String encryptedResult = fpe.encrypt(theDate);
            String decryptedResult = fpe.decrypt(encryptedResult);
            logger.info("data = [{}], encryptedResult = [{}], decryptedResult = [{}]",
                    theDate,
                    encryptedResult,
                    decryptedResult);

        }


    }



    @Test
    public void testNumber() {
        FPE fpe = new FPE(Algo.FF1_CARD10, FPECharset.CARD10);
        String res = fpe.encrypt("7312042510720002");
        assertEquals("8457934211990371",res);
    }

}
