package com.kefelan.messaging.kefelanmicroservicesauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;    

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;  
//
//    @Value("${resource.id:spring-boot-application}")
//    private String resourceId;  
//
//    @Value("${access_token.validity_period:3600}")
//    private int accessTokenValiditySeconds = 3600;  

//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }

    
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(this.authenticationManager);
//        endpoints.accessTokenConverter(accessTokenConverter());
//        endpoints.tokenStore(tokenStore());
//    }
//
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
//    }
//
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("app")
//                .authorizedGrantTypes("authorization_code", "implicit")
//                .authorities("ROLE_CLIENT")
//                .scopes("read","write")
//                .resourceIds(resourceId)
//                .accessTokenValiditySeconds(accessTokenValiditySeconds);
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter(){
//        return new JwtAccessTokenConverter(){
//
//            @Override
//            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//
//                String userName = authentication.getUserAuthentication().getName();
//                User user = (User) authentication.getUserAuthentication().getPrincipal();
//                Map<String,Object> infoMap = new HashMap<>();
//                infoMap.put("userName",userName);
//                infoMap.put("roles",user.getAuthorities());
//                ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(infoMap);
//                return super.enhance(accessToken, authentication);
//            }
//        };
//    }
    
    
//    @Bean
//    public TokenStore tokenStore() {
//        return new RedisTokenStore(redisConnectionFactory);
//    }

    @Autowired(required = false)
    private TokenStore inMemoryTokenStore;
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        //endpoints.tokenStore(tokenStore());
        endpoints.tokenStore(inMemoryTokenStore);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        clients.inMemory()
                .withClient("android")
                .scopes("xx")
                .secret(encoder.encode("android"))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .and()
                .withClient("webapp")
                .scopes("xx")
                .authorizedGrantTypes("implicit");
    }
}
