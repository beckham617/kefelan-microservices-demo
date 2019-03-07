package com.kefelan.messaging.kefelanmicroservicesauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
* 授权服务器配置
* @ EnableAuthorizationServer 启用授权服务
*/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;    // 认证管理器

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;  // redis连接工厂

    /**
     * 令牌存储
     * @return redis令牌存储对象
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("android")
                .scopes("xx")
                .secret("{noop}android")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .and()
                .withClient("webapp")
                .scopes("xx")
                .authorizedGrantTypes("implicit");
    }

//	@Autowired
//    private AuthenticationManager authenticationManager;
// 
//    @Autowired
//    private UserDetailsService userService;
// 
//    @Autowired
//    private TokenStore tokenStore;
// 
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("test")//客户端ID
//                .authorizedGrantTypes("password", "refresh_token")//设置验证方式
//                .scopes("read", "write")
//                .secret("{noop}123456")
//                .accessTokenValiditySeconds(10000) //token过期时间
//                .refreshTokenValiditySeconds(10000); //refresh过期时间
//    }
// 
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore)
//                .authenticationManager(authenticationManager)
//                .userDetailsService(userService); //配置userService 这样每次认证的时候会去检验用户是否锁定，有效等
//    }
// 
//    @Bean
//    public TokenStore tokenStore() {
//        //使用内存的tokenStore
//        return new InMemoryTokenStore();
//    }

}