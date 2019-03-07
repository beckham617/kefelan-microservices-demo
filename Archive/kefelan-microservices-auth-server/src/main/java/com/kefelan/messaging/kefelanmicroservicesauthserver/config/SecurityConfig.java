package com.kefelan.messaging.kefelanmicroservicesauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.kefelan.messaging.kefelanmicroservicesauthserver.security.DomainUserDetailsService;

/**
* 安全配置
* @ EnableWebSecurity 启用web安全配置
* @ EnableGlobalMethodSecurity 启用全局方法安全注解，就可以在方法上使用注解来对请求进行过滤
*/
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    /**
//     * 注入用户信息服务
//     * @return 用户信息服务对象
//     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new DomainUserDetailsService();
//    }

//    /**
//     * 全局用户信息
//     * @param auth 认证管理
//     * @throws Exception 用户认证异常信息
//     */
//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//        
//        //可以设置内存指定的登录的账号密码,指定角色
//        //不加.passwordEncoder(new MyPasswordEncoder()), 就不是以明文的方式进行匹配，会报错
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//        //.passwordEncoder(new MyPasswordEncoder())。 这样，页面提交时候，密码以明文的方式进行匹配。
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("cxh").password("cxh").roles("ADMIN");
//        
//    }

//    /**
//     * 认证管理
//     * @return 认证管理对象
//     * @throws Exception 认证异常信息
//     */
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

//    /**
//     * http安全配置
//     * @param http http安全对象
//     * @throws Exception http安全异常信息
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated().and()
//                .httpBasic().and().csrf().disable();
//    }

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	    .authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();
	}

	
}
