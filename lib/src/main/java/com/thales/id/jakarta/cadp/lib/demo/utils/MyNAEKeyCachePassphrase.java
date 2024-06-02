package com.thales.id.jakarta.cadp.lib.demo.utils;

import com.ingrian.security.nae.NAEKeyCachePassphrase;
import com.ingrian.security.nae.NAESessionInterface;

/**
 * sandy.haryono@thalesgroup.com
 * NAE Key Cache Pass Phrase
 */

public class MyNAEKeyCachePassphrase implements NAEKeyCachePassphrase {

    @Override
    public char[] getPassphrase(NAESessionInterface session) {
        char[] passPhrase = new char[8];
        passPhrase[0] = 'a';
        passPhrase[1] = 'c';
        passPhrase[2] = 'b';
        passPhrase[3] = '1';
        passPhrase[4] = '2';
        passPhrase[5] = '4';
        passPhrase[6] = '7';
        passPhrase[7] = 'z';
        return passPhrase;
    }
}