package com.halloid.sdk.service;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        this.tokenService = TokenService.getInstance();
    }

    @Test
    void shouldReturnValidTokenServiceInstance() {
    }

    @Test
    void shouldReturnSignedJWT() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String clientID = "TEST_CLIENT_ID";
        String privateKey = this.getPrivateKey();

        String token = this.tokenService.generateSignedJWT(clientID, privateKey);

        MatcherAssert.assertThat(token, CoreMatchers.notNullValue());
    }

    @Test
    void shouldReturnVerifiedJWT() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String clientID = "TEST_CLIENT_ID";
        String privateKey = this.getPrivateKey();

        String token = this.tokenService.generateSignedJWT(clientID, privateKey);

        MatcherAssert.assertThat(true, CoreMatchers.is(this.tokenVerified(token)));
    }

    private Boolean tokenVerified(String jwt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return this.tokenService.verifyJWTSignature(this.getPublicKey(), jwt).getTokenVerified();
    }

    private String getPrivateKey() {
        return "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEBvU8aQd+hDBUu1veJIoE9lDUTSv5xBg9OMuN6Zg56NfeSz" +
                "TBolfgi3BWQM+C0vPQ5bjIDnBMPr+3tY723uPabF04O/4eWi6Vfv+dJvMa8eavP/V7HhmjeYjU5maBE1aJr1n0KItsq0imhNv" +
                "trk1pBUexfczG079suyGr8hjjq21S6ptdMqNZyB7PekMCSj7fsV0MY74t8GkR888jOsL3bqO54t3CjiMESk9M0sty1W8CUmML" +
                "Oag+y9gLmPnMOkJAv9ypUdRYFnHpM9n8fZMACrA60+yhi2AE1Pilths7Ov9bMIZ66i6Ea1FoEKC9QVPNzASXmEzDiW0oK5GYs" +
                "OHpAgMBAAECggEAP4nps+0IAkczy1Nv3vTOx5yTQdjx9P3+fFg8/ksRS61XVk/zT80FZbc1lQ0T0O4dEMe9qeno+YipU4w5BW" +
                "43UgMcydd1cL4N9TxtYABCtxD76BNr3Jib1iTDSVKoui3IU/Lj47EviFjWWe1OupBMCOLVogHc70MwuO9GQS399HL0YZs4gpK" +
                "/NEJuzT8wwodTKV3C8Luk3DiPq8BNuAmgr7ekqIVpnvVrfkpA/aB8i11qm7+ZLliyle95N/C1Ht9tXIgagBV/rYDGpKDkGaRp" +
                "mv4WKBojLfOg4RIzWmR/Sdrf2EL6bs4wqJfWjenfQNqw4YwbpOO7GZg5bX9bO25ZQQKBgQDZ5+o+ac/aprBhM8AdY1s4TGERV" +
                "8A8zl4LFxRwOdx51sIr+qmNzBTKUs3xZo21+hB+lc29CtTYqBTzsbdsa7Rnh9NLMknI9g74LkC9ZoWP0eKZerFNnqbf/sFvXi" +
                "Guv3fwGlOGPsYAWEbK6+9DStzf3ao+UDlj6s/A/L2dCttYbQKBgQCbG6fwZmOUyxrVJjbFNpXCz9L4w/EnHfHq4gkkun3esNs" +
                "N78b6zLJ3zHviuMWiaTOqxfp+u8bBYSCs5xh6MU/T7+kqxXBDuIRPDsAAzmRLhV1bf0v9U4yv6A1U5+8fQi5Q19fRHHsyC7yj" +
                "h0NQ09/HoqA5Ag80MOkMpZAatlb57QKBgA3qMloj9aL3zAGZBWabBqM0tWofGWaIoxbANYzpFPwuy511V0h3xMgau43zx47cV" +
                "htPQ6ETxP3E1fmt2pGw2wdVdBe0MWQE2nXctkOh/VtuwrKcVCApxmw9X2rWLv5yuZz6gWTsM0aoXHTu1guNB0fAZvOtucx4nS" +
                "Wsvgr+O32NAoGAYpHAt9VZk0U/WBVg2RNHOPY5bD2ZEvLEui/K/7xEITDjBIhMOzW6dMdOM1aYwg14+B24grFyLZI74aU6uOx" +
                "9foIH+1Zdcavg6RzGh7yhoBywp2TONb7SuTcOwdZi78pvBJEmZY1j/PnYd1OOQ99hZszCZXgZkWFDuPW+DjuyKkECgYAw6e8/" +
                "4FIN1Jy8uXXqE2ORbfIr7GFFxbPatbr67DhuE9v7jqvFDrSjOIgRAuj+srzUHyhJrkoUui/NUAECg9fzQeh0ZOKYkQ1aFpnEv" +
                "CrPXkPrf9fpDIRePnS2n7GdktE5/umtr4nGe0V5Y6qkJEsTmALxtkzIUFdz848bAXFDQQ==";
    }

    private String getPublicKey() {
        return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhAb1PGkHfoQwVLtb3iSKBPZQ1E0r+cQYPTjLjemYOejX3ks0waJX4I" +
                "twVkDPgtLz0OW4yA5wTD6/t7WO9t7j2mxdODv+HloulX7/nSbzGvHmrz/1ex4Zo3mI1OZmgRNWia9Z9CiLbKtIpoTb7a5NaQV" +
                "HsX3MxtO/bLshq/IY46ttUuqbXTKjWcgez3pDAko+37FdDGO+LfBpEfPPIzrC926jueLdwo4jBEpPTNLLctVvAlJjCzmoPsvY" +
                "C5j5zDpCQL/cqVHUWBZx6TPZ/H2TAAqwOtPsoYtgBNT4pbYbOzr/WzCGeuouhGtRaBCgvUFTzcwEl5hMw4ltKCuRmLDh6QIDA" +
                "QAB";
    }

}
