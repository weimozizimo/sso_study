package boot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {
    //AuthorizationServerConfigurerAdapter这个类提供了授权服务器策略
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void configure(final  AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//    }
//
//    @Override
//    public void configure(final  ClientDetailsServiceConfigurer clients) throws Exception {
//
//        clients.inMemory()
//                .withClient("handleCilentId")//客户端id
//                .secret(passwordEncoder.encode("secret"))//客户端密钥
//                .authorizedGrantTypes("authorization_code")//授权码模式
//                .scopes("use_info")//授权范围
//                .autoApprove(true)//开启自动授权
//                .redirectUris("http://localhost:8082/login","http://localhost:8084/login")//认证成功重定向
//                .accessTokenValiditySeconds(10);//设置超时时间
//    }


//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource oauthDataSource(){
//        return DataSourceBuilder.create().build();
//    }
    @Autowired
    private DataSource oauthDataSource;

    @Bean
    public JdbcClientDetailsService  clientDetailsService(){
        return new JdbcClientDetailsService(oauthDataSource);
    }

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(oauthDataSource);
    }

    @Bean
    public ApprovalStore approvalStore(){
        return new JdbcApprovalStore(oauthDataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new JdbcAuthorizationCodeServices(oauthDataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices())
                .tokenStore(tokenStore());
    }
}
