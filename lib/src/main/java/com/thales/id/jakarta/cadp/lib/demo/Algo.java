package com.thales.id.jakarta.cadp.lib.demo;

/**
 * sandy.haryono@thalesgroup.com
 * an Enum class for mapping all the algorithms
 * <a href="https://thalesdocs.com/ctp/con/cadp/cadp-java/latest/admin/index.html">
 *     https://thalesdocs.com/ctp/con/cadp/cadp-java/latest/admin/index.html
 * </a>
 *
 */

public enum Algo {

    //FF1
    FF1_CARD10("FPE/FF1/CARD10"),
    FF1_CARD26("FPE/FF1/CARD26"),
    FF1_CARD62("FPE/FF1/CARD62"),
    FF1_UNICODE("FPE/FF1/UNICODE"),

    //FF1v2
    FF1v2_CARD10("FPE/FF1v2/CARD10"),
    FF1v2_CARD26("FPE/FF1v2/CARD26"),
    FF1v2_CARD62("FPE/FF1v2/CARD62"),
    FF1v2_UNICODE("FPE/FF1v2/UNICODE"),

    //FF3
    FF3_CARD10("FPE/FF3/CARD10"),
    FF3_CARD26("FPE/FF3/CARD26"),
    FF3_CARD62("FPE/FF3/CARD62"),
    FF3_UNICODE("FPE/FF3/UNICODE"),

    //AES
    AES_CBC_NO_PADDING("AES/CBC/NoPadding"),
    AES_CBC_PKCS_5_PADDING("AES/CBC/PKCS5Padding"),
    AES_ECB_NO_PADDING("AES/ECB/NoPadding"),
    AES_ECB_PKCS_5_PADDING("AES/ECB/PKCS5Padding"),
    AES_GCM_NO_PADDING("AES/GCM/NoPadding"),
    AES_CTR_NO_PADDING("AES/CTR/NoPadding")
    ;


    private String value;
    Algo(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public String toString(){return value;}
}
