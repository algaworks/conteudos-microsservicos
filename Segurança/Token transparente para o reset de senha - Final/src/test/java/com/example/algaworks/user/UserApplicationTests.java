package com.example.algaworks.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;

import java.util.Base64;
import java.util.Date;
import java.util.Random;

class UserApplicationTests {

    @Test
    void createToken() throws Exception {
        KeyBasedPersistenceTokenService service = new KeyBasedPersistenceTokenService();
        service.setServerSecret("SECRET123");
        service.setServerInteger(16);
        service.setSecureRandom(new SecureRandomFactoryBean().getObject());

        Token token = service.allocateToken("maria@email.com");

        System.out.println(token.getExtendedInformation());
        System.out.println(new Date(token.getKeyCreationTime()));
        System.out.println(token.getKey());


        //MTY2NjIwNTU2NTI0ODo1YzI3OGVlZDBmYmM3NDk3MTZjOTlkNDFlMjZlNjRiOTVhNGRiY2RjYTQwOTRkZTE2ZWMzYjg1NTQ1ZmQ0YzQxOm1hcmlhQGVtYWlsLmNvbTpmYzI2OTk2OTczY2E0NjU5NDk3N2FlYTdlMjIyMjYyNTcyZDUxYWFlMjA4YzU4ZDZlMDgwMDFkYzhlM2ZhMWJhOWQ2ZTc1ZDZhNGVjM2RiNGM5NjI3YjdjZTI4NjQ0MWNiOGZiMmFkNzFhNWQwNzkyYmQwZGQ3YTk3N2NlMzg2Zg==

        // Wed Oct 19 15:52:45 BRT 2022
    }

    @Test
    public void readToken() throws Exception {
        KeyBasedPersistenceTokenService service = new KeyBasedPersistenceTokenService();
        service.setServerSecret("SECRET123");
        service.setServerInteger(16);
        service.setSecureRandom(new SecureRandomFactoryBean().getObject());

        String rawToken = "MTY2NjIwNTU2NTI0ODo1YzI3OGVlZDBmYmM3NDk3MTZjOTlkNDFlMjZlNjRiOTVhNGRiY2RjYTQwOTRkZTE2ZWMzYjg1NTQ1ZmQ0YzQxOm1hcmlhQGVtYWlsLmNvbTpmYzI2OTk2OTczY2E0NjU5NDk3N2FlYTdlMjIyMjYyNTcyZDUxYWFlMjA4YzU4ZDZlMDgwMDFkYzhlM2ZhMWJhOWQ2ZTc1ZDZhNGVjM2RiNGM5NjI3YjdjZTI4NjQ0MWNiOGZiMmFkNzFhNWQwNzkyYmQwZGQ3YTk3N2NlMzg2Zg==";

        Token token = service.verifyToken(rawToken);

        System.out.println(token.getExtendedInformation());
        System.out.println(new Date(token.getKeyCreationTime()));
        System.out.println(token.getKey());
    }

    @Test
    public void readPublicTokenInfo() throws Exception {
        String rawToken = "MTY2NjIwNTU2NTI0ODo1YzI3OGVlZDBmYmM3NDk3MTZjOTlkNDFlMjZlNjRiOTVhNGRiY2RjYTQwOTRkZTE2ZWMzYjg1NTQ1ZmQ0YzQxOm1hcmlhQGVtYWlsLmNvbTpmYzI2OTk2OTczY2E0NjU5NDk3N2FlYTdlMjIyMjYyNTcyZDUxYWFlMjA4YzU4ZDZlMDgwMDFkYzhlM2ZhMWJhOWQ2ZTc1ZDZhNGVjM2RiNGM5NjI3YjdjZTI4NjQ0MWNiOGZiMmFkNzFhNWQwNzkyYmQwZGQ3YTk3N2NlMzg2Zg==";

        byte[] bytes = Base64.getDecoder().decode(rawToken);
        String rawTokenDecoded = new String(bytes);

        System.out.println(rawTokenDecoded);

        String[] tokenParts = rawTokenDecoded.split(":");

        Long timestamp = Long.parseLong(tokenParts[0]);
        System.out.println(new Date(timestamp));
        System.out.println(tokenParts[2]);
    }

}
