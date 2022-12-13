package com.example.demo;

import java.util.HashMap;
import java.math.BigInteger;

public class IbanValidatorImpl {

    private static IbanValidatorImpl instance;
    public boolean ibanIsValid;
    public String description;
    // mapping according https://www.iban.com/structure
    private static HashMap<String, Integer> ibanLengthByCountryCode = ibanLengthByCCMap.ibanLengthByCountryCode;
    private static HashMap<String, Integer> letterReplacementCodesMap = letterReplacementMap.letterReplacementCodesMap;

    public static IbanValidatorImpl validateIban(String ibanValue) {
        var countryCode = ibanValue.substring(0, 2).toUpperCase();
        var ibanLength = ibanValue.length();

        if (ibanLengthByCountryCode.containsKey(countryCode)) {
            // check for length according the country code
            if (ibanLength == ibanLengthByCountryCode.get(countryCode)) {
                var modifiedIbanString = ibanValue.substring(4) + "" + ibanValue.substring(0, 4);
                //replace all the letters with two digit code
                for (var k : letterReplacementCodesMap.keySet()) {
                    modifiedIbanString = modifiedIbanString.replaceAll(String.valueOf(k), String.valueOf(letterReplacementCodesMap.get(k)));
                }
                var numericInput = new BigInteger(modifiedIbanString);

                if (numericInput.remainder(BigInteger.valueOf(97)).intValue() == 1) {
                    instance = new IbanValidatorImpl(true, "OK");
                } else {
                    instance = new IbanValidatorImpl(false, "IBAN failed mod97 rule.");
                }
            } else {
                instance = new IbanValidatorImpl(false, "IBAN length is wrong for this country code.");
            }
        } else {
            instance = new IbanValidatorImpl(false, "Specified country code does not exist.");
        }
        return instance;
    }

    public IbanValidatorImpl(boolean ibanIsValid, String description) {
        this.ibanIsValid = ibanIsValid;
        this.description = description;
    }

}
