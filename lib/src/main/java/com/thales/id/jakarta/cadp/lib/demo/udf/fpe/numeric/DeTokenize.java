package com.thales.id.jakarta.cadp.lib.demo.udf.fpe.numeric;

import com.ingrian.security.nae.FPECharset;
import com.thales.id.jakarta.cadp.lib.demo.Algo;
import com.thales.id.jakarta.cadp.lib.demo.fpe.FPE;
import org.apache.spark.sql.api.java.UDF1;

import java.io.Serializable;

/**
 * Author :
 * sandy.haryono@thalesgroup.com
 * This class is for UDF functionality
 * call it via  :
 *
 * com.thales.id.jakarta.cadp.lib.demo.udf.fpe.numeric.DeTokenize
 * com.thales.id.jakarta.cadp.lib.demo.udf.fpe.numeric.Tokenize
 *
 * */
public class DeTokenize implements UDF1<String,String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String call(String data)
    {
        FPE fpe = new FPE(Algo.FF1_CARD10, FPECharset.CARD10);
        return fpe.decrypt(data);
    }
}
