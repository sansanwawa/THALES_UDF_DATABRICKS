package com.thales.id.jakarta.cadp.lib.demo.service;

import java.nio.ByteBuffer;

/**
 * sandy.haryono@thalesgroup.com
 * This is a code for generate Tweak base on the key name provided
 */
public class TweakGenerator {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String getTweakFromKeyName(String keyName) {
        byte[] tweak = longToBytes(ullHash(keyName));
        ByteBuffer buffer = ByteBuffer.allocate(tweak.length);
        for (int i = (tweak.length - 1); i >= 0; i--) {
            buffer.put(tweak[i]);
        }
        return byteArrayToString(buffer.array());
    }

    private static long ullHash(String inputKey) {
        long ull = 65537L;
        int i = 0;

        char[] input = inputKey.toCharArray();
        while (i < inputKey.length())
            ull = ull * 3 + (int) input[i++];
        return ull;

    }

    private static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }


    private static String byteArrayToString(byte[] bytes) {
        return bytesToHex(bytes);
    }


    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}
