# Spring Boot REST API for IBAN validation

This application is developed using Spring Boot framework on VSCode. Application verifies IBAN codes from different countries according information provided in [IBAN official website](https://www.iban.com/structure).

Four different responses are possible:
* Validation passes with response as description: "OK" and iban_is_valid: true.
* Validation fails with response iban_is_valid: false and
    * description: "Specified country code does not exist.", when there is no country code specified in ibanLengthByCCMap.
    * description: "IBAN length is wrong for this country code.", when length of the IBAN code is different than length of the IBAN for specified country code.
    * description: "IBAN failed mod97 rule.", when final rule of modulus 97 does not pass.

Application uses local port 3456 and get method /validator with query item IBAN, once the server is running, validation might be checked with request: http://localhost:3456/validator?IBAN=LT601010012345678901 or any other IBAN code.
