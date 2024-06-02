package com.thales.id.jakarta.cadp.lib.demo.fpe;

import com.ingrian.security.nae.*;
import com.thales.id.jakarta.cadp.lib.demo.utils.CryptoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;

/**
 * sandy.haryono@thalesgroup.com
 */

public class KeyManagement extends CADPKeyManagement{

    private static final Logger logger = LogManager.getLogger(KeyManagement.class);

    public KeyManagement(){
        CryptoUtils.init();
    }


    public void wrapKey(){
        Security.addProvider(new IngrianProvider());

        String userName = CryptoUtils.CADP_USERNAME_DEFAULT;
        String passWord = CryptoUtils.CADP_PASSWORD_DEFAULT;
        String keyToWrapName = "WrapSampleKey";
        String wrappingKeyName = "RSAKey";
        String groupName = "admin";
        NAESession session = null;
        try {
            // Create an NAESession.
            session = NAESession.getSession(userName,passWord.toCharArray());
            NAEParameterSpec spec = new NAEParameterSpec(keyToWrapName, true,true, 256, session);

            // Generate an AES key to be wrapped when exported.

            KeyGenerator generator = KeyGenerator.getInstance("AES","IngrianProvider");
            generator.init(spec); // NAEEParameters to pass session
            NAEKey keyToBeWrapped = (NAEKey) generator.generateKey();

            //Key key = NAEKey.getSecretKey(CryptoUtils.CADP_KEY_NAME_DEFAULT, session);

            //byte[] rawKey = new byte[]{0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01};
            //NAEKey keyToBeWrapped = NAEKey.importKey(rawKey,"AES",spec);
            //Key key = NAEKey.getSecretKey(CryptoUtils.CADP_KEY_NAME_DEFAULT, session);



            // Create a public/private RSA key pair to do the key wrapping.
            // The AES key will be wrapped with the RSA Public Key, and
            // later unwrapped using the RSA Private Key.
            //KeyPair pair = createKeyPair(session, groupName, wrappingKeyName);

            NAEPublicKey publicKey = NAEKey.getPublicKey(wrappingKeyName,session);
            NAEPrivateKey privateKey = NAEKey.getPrivateKey(wrappingKeyName,session);

            // Init a JCE Cipher in WRAP_MODE to do the key wrapping.
            Cipher cipher = Cipher.getInstance("RSA", "IngrianProvider");
            cipher.init(Cipher.WRAP_MODE, publicKey, spec);


            //sandy
            /*
            byte[] appKey = new byte[]{0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01};
            Cipher cipherX = Cipher.getInstance("DESede/ECB/NoPadding");
            cipherX.init(Cipher.WRAP_MODE,secretKeySpec);
            byte[] resKeyTobeSent = cipherX.wrap(secretKeySpec);
            */
            //byte[] keyToBeSent = new byte[]{0x02,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01};
            //SecretKeySpec secretKeySpec = new SecretKeySpec(keyToBeSent,"AES");


            byte[] wrappedKey = cipher.wrap(keyToBeWrapped);
            //byte[] wrappedKey = cipher.wrap(secretKeySpec);
            logger.info("wrapped  :{} ", IngrianProvider.byteArray2Hex(wrappedKey));
            logger.info("Length   :{}", wrappedKey.length);

            // Export the NAEPrivate key as a JCE PrivateKey.
            PrivateKey prKey = privateKey.exportJCEKey();
            Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
            cipher2.init(Cipher.UNWRAP_MODE, prKey);

            // Unwrap the wrapped key from the bytes returned from the
            // Key Manager.
            Key unWrappedKey = cipher2.unwrap(wrappedKey, "AES",Cipher.SECRET_KEY);

            logger.debug("Unwrapped: {}", IngrianProvider.byteArray2Hex(unWrappedKey.getEncoded()));
            logger.debug("Original : {}", IngrianProvider.byteArray2Hex(keyToBeWrapped.export()));
            if (Arrays.equals(keyToBeWrapped.export(),unWrappedKey.getEncoded()))
                logger.debug("Unwrapped key bytes equal original key bytes");

        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(session!=null)
                session.closeSession();
        }

    }



    public void importKey(){
        String userName = CryptoUtils.CADP_USERNAME_DEFAULT;
        String passWord = CryptoUtils.CADP_PASSWORD_DEFAULT;
        String keyName   = "testImport";
        String group     = "admin";

        // add Ingrian provider to the list of JCE providers
        Security.addProvider(new IngrianProvider());

        // get the list of all registered JCE providers
        Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++)
            logger.debug(providers[i].getInfo());


        try {
            session  = NAESession.getSession(userName, passWord.toCharArray());

            byte[] keyData = new byte[]{0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01};

            NAEPermission permission = new NAEPermission(group);
            // add permission to encrypt
            permission.setEncrypt (true);
            NAEPermission[] permissions = {permission};

            NAEParameterSpec spec_dup = new NAEParameterSpec(keyName, true, true, session, permissions);
            String res = NAEKey.importKey (keyData, "AES", spec_dup);
            logger.debug("Key data has been imported, the key name is : {}", res);



        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(session!=null)
                session.closeSession();
        }

    }





}
