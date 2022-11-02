package com.halloid.sdk;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HalloIDJavaSDKTest {

    private HalloIDJavaSDK halloIDJavaSDK;

    @BeforeEach
    void setUp() {
        this.halloIDJavaSDK = new HalloIDJavaSDK("TEST_CLIENT", getPrivateKey(), getPublicKey());
    }

    @Test
    void shouldReturnValidJWT() {
        String token = this.halloIDJavaSDK.generateServiceToken();

        MatcherAssert.assertThat(token, CoreMatchers.notNullValue());
    }

    @Test
    void shouldReturnVerifiedJWT() {
        String token = this.halloIDJavaSDK.generateServiceToken();

        MatcherAssert.assertThat(true, CoreMatchers.is(this.tokenVerified(token)));
    }

    private Boolean tokenVerified(String jwt) {
        return this.halloIDJavaSDK.validateJWT(jwt).getTokenVerified();
    }

    private String getPrivateKey() {
        return "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEBvU8aQd+hDBU\n" +
                "u1veJIoE9lDUTSv5xBg9OMuN6Zg56NfeSzTBolfgi3BWQM+C0vPQ5bjIDnBMPr+3\n" +
                "tY723uPabF04O/4eWi6Vfv+dJvMa8eavP/V7HhmjeYjU5maBE1aJr1n0KItsq0im\n" +
                "hNvtrk1pBUexfczG079suyGr8hjjq21S6ptdMqNZyB7PekMCSj7fsV0MY74t8GkR\n" +
                "888jOsL3bqO54t3CjiMESk9M0sty1W8CUmMLOag+y9gLmPnMOkJAv9ypUdRYFnHp\n" +
                "M9n8fZMACrA60+yhi2AE1Pilths7Ov9bMIZ66i6Ea1FoEKC9QVPNzASXmEzDiW0o\n" +
                "K5GYsOHpAgMBAAECggEAP4nps+0IAkczy1Nv3vTOx5yTQdjx9P3+fFg8/ksRS61X\n" +
                "Vk/zT80FZbc1lQ0T0O4dEMe9qeno+YipU4w5BW43UgMcydd1cL4N9TxtYABCtxD7\n" +
                "6BNr3Jib1iTDSVKoui3IU/Lj47EviFjWWe1OupBMCOLVogHc70MwuO9GQS399HL0\n" +
                "YZs4gpK/NEJuzT8wwodTKV3C8Luk3DiPq8BNuAmgr7ekqIVpnvVrfkpA/aB8i11q\n" +
                "m7+ZLliyle95N/C1Ht9tXIgagBV/rYDGpKDkGaRpmv4WKBojLfOg4RIzWmR/Sdrf\n" +
                "2EL6bs4wqJfWjenfQNqw4YwbpOO7GZg5bX9bO25ZQQKBgQDZ5+o+ac/aprBhM8Ad\n" +
                "Y1s4TGERV8A8zl4LFxRwOdx51sIr+qmNzBTKUs3xZo21+hB+lc29CtTYqBTzsbds\n" +
                "a7Rnh9NLMknI9g74LkC9ZoWP0eKZerFNnqbf/sFvXiGuv3fwGlOGPsYAWEbK6+9D\n" +
                "Stzf3ao+UDlj6s/A/L2dCttYbQKBgQCbG6fwZmOUyxrVJjbFNpXCz9L4w/EnHfHq\n" +
                "4gkkun3esNsN78b6zLJ3zHviuMWiaTOqxfp+u8bBYSCs5xh6MU/T7+kqxXBDuIRP\n" +
                "DsAAzmRLhV1bf0v9U4yv6A1U5+8fQi5Q19fRHHsyC7yjh0NQ09/HoqA5Ag80MOkM\n" +
                "pZAatlb57QKBgA3qMloj9aL3zAGZBWabBqM0tWofGWaIoxbANYzpFPwuy511V0h3\n" +
                "xMgau43zx47cVhtPQ6ETxP3E1fmt2pGw2wdVdBe0MWQE2nXctkOh/VtuwrKcVCAp\n" +
                "xmw9X2rWLv5yuZz6gWTsM0aoXHTu1guNB0fAZvOtucx4nSWsvgr+O32NAoGAYpHA\n" +
                "t9VZk0U/WBVg2RNHOPY5bD2ZEvLEui/K/7xEITDjBIhMOzW6dMdOM1aYwg14+B24\n" +
                "grFyLZI74aU6uOx9foIH+1Zdcavg6RzGh7yhoBywp2TONb7SuTcOwdZi78pvBJEm\n" +
                "ZY1j/PnYd1OOQ99hZszCZXgZkWFDuPW+DjuyKkECgYAw6e8/4FIN1Jy8uXXqE2OR\n" +
                "bfIr7GFFxbPatbr67DhuE9v7jqvFDrSjOIgRAuj+srzUHyhJrkoUui/NUAECg9fz\n" +
                "Qeh0ZOKYkQ1aFpnEvCrPXkPrf9fpDIRePnS2n7GdktE5/umtr4nGe0V5Y6qkJEsT\n" +
                "mALxtkzIUFdz848bAXFDQQ==\n" +
                "-----END PRIVATE KEY-----";
    }

    private String getPublicKey() {
        return "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhAb1PGkHfoQwVLtb3iSK\n" +
                "BPZQ1E0r+cQYPTjLjemYOejX3ks0waJX4ItwVkDPgtLz0OW4yA5wTD6/t7WO9t7j\n" +
                "2mxdODv+HloulX7/nSbzGvHmrz/1ex4Zo3mI1OZmgRNWia9Z9CiLbKtIpoTb7a5N\n" +
                "aQVHsX3MxtO/bLshq/IY46ttUuqbXTKjWcgez3pDAko+37FdDGO+LfBpEfPPIzrC\n" +
                "926jueLdwo4jBEpPTNLLctVvAlJjCzmoPsvYC5j5zDpCQL/cqVHUWBZx6TPZ/H2T\n" +
                "AAqwOtPsoYtgBNT4pbYbOzr/WzCGeuouhGtRaBCgvUFTzcwEl5hMw4ltKCuRmLDh\n" +
                "6QIDAQAB\n" +
                "-----END PUBLIC KEY-----";
    }


}
