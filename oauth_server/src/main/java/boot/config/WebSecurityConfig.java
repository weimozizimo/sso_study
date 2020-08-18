package boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                //配置security请求过滤，只接受下面两个路径的请求
                .antMatchers("/login")
                .antMatchers("/oauth/authorize")
                .and()
                //下面两句代表除了以上所有请求都需要身份认证才能访问
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                //代表采用form表单默认登录页面是/login，任何人都能访问，关闭csrf的保护
                .formLogin().loginPage("/login").permitAll()
                .and().csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用内存模拟数据库查询的用户
        auth.inMemoryAuthentication()//内存认证
                .withUser("admin")//登录用户名admin
                .password(passwordEncoder().encode("123456"))//被加密的123456密码
                .roles("ADMIN");//ROLE_ADMIN的角色
    }

    //PasswordEncoder是Spring官方提供的一个md5密码计米器，一般用于密码的加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
