package boot.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {
    //AuthorizationServerConfigurerAdapter这个类提供了授权服务器策略
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(final  AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final  ClientDetailsServiceConfigurer clients) throws Exception {
        DataSource dataSource = new DruidDataSource();

        clients.inMemory()
                .withClient("handleCilentId")//客户端id
                .secret(passwordEncoder.encode("secret"))//客户端密钥
                .authorizedGrantTypes("authorization_code")//授权码模式
                .scopes("use_info")//授权范围
                .autoApprove(true)//开启自动授权
                .redirectUris("http://localhost:8082/login","http://localhost:8084/login")//认证成功重定向
                .accessTokenValiditySeconds(10);//设置超时时间
    }

}
