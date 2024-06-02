package com.thales.id.jakarta.cadp.lib.demo.utils;

import com.ingrian.security.nae.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.*;
import java.security.*;
import java.util.Arrays;
import java.util.Properties;


/**
 * Author :
 * sandy.haryono@thalesgroup.com
 */

public class CryptoUtils {

    private static final Logger logger = LogManager.getLogger(CryptoUtils.class);

    /**
     * Base parameter for CADP
     */

    public static String CADP_NAE_IP 			            = "YOUR_CM_IP";
    public static String CADP_NAE_PORT 		                = "YOUR_CM_NAE_PORT";
    public static String CADP_USERNAME_DEFAULT              = "YOUR_CADP_USERNAME";
    public static String CADP_PASSWORD_DEFAULT              = "YOUR_CADP_PASSWORD";
    public static String CADP_KEY_NAME_DEFAULT              = "YOUR_CADP_KEY_DEFAULT";
    public static String CADP_NAE_DNS_RESOLUTION_FAILURE    = "false";
    public static String CADP_NAE_KMIP_PORT 				= "5696";
    public static String CADP_NAE_PROTOCOL				    = "tcp"; //tcp/ssl
    public static String CADP_NAE_VERIFY_SSL				= "no";
    public static String CADP_NAE_LOAD_BALANCING_ALG		= "round-robin";

 

    public static void init(){

        logger.info("--------------------------INITIALIZING----------------------------");
        logger.info("CADP_NAE_IP = {}",CADP_NAE_IP);
        logger.info("CADP_NAE_PORT = {}",CADP_NAE_PORT);
        logger.info("CADP_NAE_DNS_RESOLUTION_FAILURE = {}",CADP_NAE_DNS_RESOLUTION_FAILURE);
        logger.info("CADP_NAE_KMIP_PORT = {}",CADP_NAE_KMIP_PORT);
        logger.info("CADP_NAE_PROTOCOL = {}",CADP_NAE_PROTOCOL);
        logger.info("CADP_NAE_VERIFY_SSL = {}",CADP_NAE_VERIFY_SSL);
        logger.info("CADP_NAE_LOAD_BALANCING_ALG = {}",CADP_NAE_LOAD_BALANCING_ALG);


        Properties property = System.getProperties();
        property.setProperty("com.ingrian.security.nae.Version","2.4");
        property.setProperty("com.ingrian.security.nae.NAE_IP.1",CADP_NAE_IP);
        property.setProperty("com.ingrian.security.nae.NAE_Port",CADP_NAE_PORT);
        property.setProperty("com.ingrian.security.nae.Ignore_DNS_Resolution_Failure",CADP_NAE_DNS_RESOLUTION_FAILURE);
        property.setProperty("com.ingrian.security.nae.KMIP_Port",CADP_NAE_KMIP_PORT);
        property.setProperty("com.ingrian.security.nae.Protocol",CADP_NAE_PROTOCOL);
        property.setProperty("com.ingrian.security.nae.Verify_SSL_Certificate",CADP_NAE_VERIFY_SSL);
        property.setProperty("com.ingrian.security.nae.Load_Balancing_Algorithm",CADP_NAE_LOAD_BALANCING_ALG);
        property.setProperty("com.ingrian.security.nae.Log_Rotation","Daily");
        property.setProperty("com.ingrian.security.nae.SSL_Handshake_Timeout","");
        property.setProperty("com.ingrian.security.nae.Symmetric_Key_Cache_Enabled","tcp_ok"); //yes or tcp_ok
        property.setProperty("com.ingrian.security.nae.Asymmetric_Key_Cache_Enabled","tcp_ok"); //yes or tcp_ok
        property.setProperty("com.ingrian.security.nae.Use_Persistent_Connections","yes");
        property.setProperty("com.ingrian.security.nae.Symmetric_Key_Cache_AutoRefresh_Interval","0");
        property.setProperty("com.ingrian.security.nae.Local_Cipher_Cache_Expiry","0");
        property.setProperty("com.ingrian.security.nae.Local_Crypto_Provider","SunJCE"); //SunJCE
        property.setProperty("com.ingrian.security.nae.Persistent_Cache_Enabled","yes"); //cache?
        property.setProperty("com.ingrian.security.nae.Persistent_Cache_Expiry_Keys","43200");
        property.setProperty("com.ingrian.security.nae.Persistent_Cache_Directory","/tmp");
        property.setProperty("com.ingrian.security.nae.Persistent_Cache_Max_Size","100");
        property.setProperty("com.ingrian.security.nae.Size_of_Connection_Pool","300");
        property.setProperty("com.ingrian.security.nae.Symmetric_Key_Cache_Expiry","43200");
        property.setProperty("com.ingrian.security.nae.Connection_Idle_Timeout","600000");
        property.setProperty("com.ingrian.security.nae.Unreachable_Server_Retry_Period","60000");
        property.setProperty("com.ingrian.security.nae.Maximum_Server_Retry_Period","0");
        property.setProperty("com.ingrian.security.nae.Connection_Timeout","1m");
        property.setProperty("com.ingrian.security.nae.Connection_Read_Timeout","7000");
        property.setProperty("com.ingrian.security.nae.Connection_Retry_Interval","600000");
        property.setProperty("com.ingrian.security.nae.Client_Cert_Alias","");
        property.setProperty("com.ingrian.security.nae.Client_Cert_Passphrase","");
        property.setProperty("com.ingrian.security.nae.Use_Etoken","no");
        property.setProperty("com.ingrian.security.nae.Etoken_Name","");
        property.setProperty("com.ingrian.security.nae.Key_Store_Location","");
        property.setProperty("com.ingrian.security.nae.Key_Store_Password","");
        property.setProperty("com.ingrian.security.nae.Cluster_Synchronization_Delay","170");
        property.setProperty("com.ingrian.security.nae.CA_File","");
        property.setProperty("com.ingrian.security.nae.Cert_File","");
        property.setProperty("com.ingrian.security.nae.Key_File","");
        property.setProperty("com.ingrian.security.nae.Passphrase","");
        property.setProperty("com.ingrian.security.nae.Credentials_Encrypted","no");
        property.setProperty("com.ingrian.security.nae.Passphrase_Encrypted","no");
        property.setProperty("com.ingrian.security.nae.Log_Level","WARN");
        property.setProperty("com.ingrian.security.nae.Log_File","");
        property.setProperty("com.ingrian.security.nae.Log_GMT","no");
        property.setProperty("com.ingrian.security.nae.Log_Size_Limit","100k");
        property.setProperty("com.ingrian.security.nae.SysLog_IP","");
        property.setProperty("com.ingrian.security.nae.SysLog_Port","");
        property.setProperty("com.ingrian.security.nae.SysLog_Protocol","");
        property.setProperty("com.ingrian.security.nae.SysLog_SSLKeystore","");
        property.setProperty("com.ingrian.security.nae.SysLog_SSLKeystorePassword","");
        property.setProperty("com.ingrian.security.nae.Log_Config_Advanced","");
        property.setProperty("com.ingrian.security.nae.Key_non_exportable_policy","no");
        property.setProperty("com.ingrian.security.nae.Log_MaxBackupIndex","-1");
        property.setProperty("com.ingrian.security.nae.SNI_HostUsed","");
        property.setProperty("com.ingrian.security.nae.ReceiveBufferSize","64");
        System.setProperties(property);


    }



}
