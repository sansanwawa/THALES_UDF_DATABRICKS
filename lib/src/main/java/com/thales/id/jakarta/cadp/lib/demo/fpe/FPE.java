package com.thales.id.jakarta.cadp.lib.demo.fpe;

import com.ingrian.security.nae.*;
import com.thales.id.jakarta.cadp.lib.demo.Algo;
import com.thales.id.jakarta.cadp.lib.demo.service.TweakGenerator;
import com.thales.id.jakarta.cadp.lib.demo.utils.MyNAEKeyCachePassphrase;
import com.thales.id.jakarta.cadp.lib.demo.utils.CryptoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * Author :
 * sandy.haryono@thalesgroup.com
 *
 * Format Preserving Encryption / FPE class
 * which stands for FF1,FF2 and FF3.
 * FF2 was created but never approved by National Institute of Standards and Technology (NIST)
 * So, it is only support FF1 and FF3
 *
 *
 * The algorithm supports different cardinalities for different kind of data.
 * CARD10: To encrypt/decrypt data consisting of digits (0-9).
 * CARD26: To encrypt/decrypt data containing lower case (a-z).
 * CARD62: To encrypt/decrypt data containing digits (0-9), lower case (a-z), and upper case (A-Z).
 * UNICODE : to encrypt/decrypt for various languages
 */

public class FPE extends CADPKeyManagement{

    private static final Logger logger = LogManager.getLogger(FPE.class);

    private String keyName;

    public FPE() {
        this.algo = Algo.FF1_CARD10.toString();
        this.charset = FPECharset.CARD10;
        init();
    }

    public FPE(String keyName) {
        //default Algo
        this.algo = Algo.FF1_CARD10.toString();
        //this.algo = Algo.FF3_CARD10.toString();
        this.charset = FPECharset.CARD10;
        this.keyName = keyName;
        init();
    }


    public FPE(Algo algo) {
        this.algo = algo.toString();
        this.charset = FPECharset.CARD10;
        init();
    }

    public FPE(Algo algo,FPECharset charset ) {
        this.algo = algo.toString();
        this.charset = charset;
        init();
    }


    private void init(){
        CryptoUtils.init();
        String keyName = this.keyName == null ? CryptoUtils.CADP_KEY_NAME_DEFAULT : this.keyName;

        String tweakData = TweakGenerator.getTweakFromKeyName(keyName);
        String tweakAlgo = "None"; //Optional must be from SHA1, SHA256 or None
        //Enable this if you want to use a cache....
        //MyNAEKeyCachePassphrase m = new MyNAEKeyCachePassphrase();
        //session = NAESession.getSession(CryptoUtils.CADP_USERNAME_DEFAULT, CryptoUtils.CADP_PASSWORD_DEFAULT.toCharArray(),m.getPassphrase(null));
        session = NAESession.getSession(CryptoUtils.CADP_USERNAME_DEFAULT, CryptoUtils.CADP_PASSWORD_DEFAULT.toCharArray());
        key     = NAEKey.getSecretKey(keyName, session);
        param = new FPEParameterAndFormatSpec.FPEParameterAndFormatBuilder(tweakData)
                .set_tweakAlgorithm(tweakAlgo)
                .set_charset(charset)
                .build();
        logger.debug("TweakData = {}", tweakData);

    }


    public String encrypt(String data) {
        return new String(encrypt(data.getBytes(StandardCharsets.UTF_8)));
    }


    public byte[] encrypt(byte[] data) {
        try {
            Cipher encryptCipher = Cipher.getInstance(this.algo, "IngrianProvider");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, param);
            return encryptCipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException |
                 NoSuchProviderException | NoSuchPaddingException |
                 IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }

    }


    public String decrypt(String data) {
        return new String(decrypt(data.getBytes(StandardCharsets.UTF_8)));
    }

    public byte[] decrypt(byte[] data) {
        try {
            Cipher encryptCipher = Cipher.getInstance(this.algo, "IngrianProvider");
            encryptCipher.init(Cipher.DECRYPT_MODE, key, param);
            return encryptCipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException |
                 NoSuchProviderException | NoSuchPaddingException |
                 IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }



    public void closeSession(){
        session.closeSession();
    }


    public void setKeyName(String keyName){
        this.keyName = keyName;
    }

    public String getKeyName(){
        return this.keyName;
    }

}
