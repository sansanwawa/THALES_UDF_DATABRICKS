package com.thales.id.jakarta.cadp.lib.demo.udf;

import com.ingrian.security.nae.IngrianProvider;
import com.ingrian.security.nae.NAEKey;
import com.ingrian.security.nae.NAESession;
import com.thales.id.jakarta.cadp.lib.demo.utils.CryptoUtils;
import com.thales.id.jakarta.cadp.lib.demo.utils.MyNAEKeyCachePassphrase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.sql.api.java.UDF1;

import java.io.Serializable;
import java.security.Key;

/**
 * Author :
 * sandy.haryono@thalesgroup.com
 * This class is for UDF functionality
 */
public class Decrypt implements UDF1<String,String>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(Decrypt.class);

    @Override
    public String call(String data){
        //if null, then display as a "" (empty string) to prevent error
            return "";
     }
}
