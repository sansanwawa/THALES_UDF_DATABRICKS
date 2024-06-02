package com.thales.id.jakarta.cadp.lib.demo.service;


/**
 *
 * sandy.haryono@thalesgroup.com
 * This is a class for checking a proper credit card value by using Luhn algorithm
 */
public class Luhn {

    public static boolean check(String cardNo)
    {
        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {
            int d = cardNo.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

}
