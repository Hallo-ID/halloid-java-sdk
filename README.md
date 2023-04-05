# HalloID Java SDK

This is the open-source SDK to integrate your Java backend with HalloID for the 
following operations:
- Service token generation (private key signature)
- Verification of returned token by HaloID (public key signature)

## Generating the library
Clone this project, then execute the build command:
```shell
./gradlew clean build
```

## Using the HalloID Java SDK

### Initializing the SDK

Find detailed documentation about the SDK configuration in 
[HaloID technical docs](https://docs.halloid.io).
```java
private final HalloIDJavaSDK halloIDJavaSDK;

// Initialize the SDK with the retrieved values from the properties file
public TokenService(@Value("${halloId.client.id}") String clientID,
                    @Value("${halloId.client.privateKey}") String privateKey,
                    @Value("${halloId.publicKey}") String halloIdPublicKey) {
    this.halloIDJavaSDK = new HalloIDJavaSDK(clientID, privateKey, halloIdPublicKey);
}
```

### Available methods

- `generateServiceToken()`
Generate service token to be sent by web client. This token will be intercepted 
by HalloID controller and verified using tenant's public key.

- `validateJWT(String token)`
Validates JWT returned by HalloID once the authentication process is complete. 
This validation is performed using HalloID public key.

## Collaborating

Feel free to submit any issue in [HalloID GitHub](https://github.com/Hallo-ID). 
If you want to go further, you can also create a PR and submit it for review.