package com.rvtjakula.authservice;

import com.rvtjakula.authservice.security.services.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.UUID;

@SpringBootTest
class UserServiceDec24ApplicationTests {
    @Autowired
    private JpaRegisteredClientRepository registeredClientRepository;

    @Test
    void contextLoads() {
    }

//    http://localhost:9090/.well-known/openid-configuration
    @Test
    void addClientToDB() {
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("oidc-client")
                .clientSecret("$2a$12$rHik.fHtYTK9tkslYrjbwe14SyFmzML8Kc5R2NmIl1vFs8ExFQS7S")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("ADMIN")
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofMinutes(10)).accessTokenFormat(
                 OAuth2TokenFormat.SELF_CONTAINED).build())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        registeredClientRepository.save(oidcClient);
    }
}
