package com.example.demo;

import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IbanValidator {

    @GetMapping(path = "/validator", produces = "application/json")
    public HashMap<String, Object> validate(@RequestParam(value = "IBAN") String ibanValue) {
        HashMap<String, Object> output = new HashMap<String, Object>();
        IbanValidatorImpl instance = IbanValidatorImpl.validateIban(ibanValue);
        output.put("iban_is_valid", instance.ibanIsValid);
        output.put("description", instance.description);
        return output;
    }

}