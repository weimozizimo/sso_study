package boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers()
//                //配置security请求过滤，只接受下面两个路径的请求
//                .antMatchers("/login")
//                .antMatchers("/oauth/authorize")
//                .and()
//                //下面两句代表除了以上所有请求都需要身份认证才能访问
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                //代表采用form表单默认登录页面是/login，任何人都能访问
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //配置登出路径
//                .and()
//                .userDetailsService(userDetailsServiceBean()); //使用userDetailsService获取用户信息
        http
                .requestMatchers()
                .antMatchers("/login")
                .antMatchers("/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .userDetailsService(userDetailsServiceBean());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //使用内存模拟数据库查询的用户
//        auth.inMemoryAuthentication()//内存认证
//                .withUser("admin")//登录用户名admin
//                .password(passwordEncoder().encode("123456"))//被加密的123456密码
//                .roles("ADMIN");//ROLE_ADMIN的角色
        auth.userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder());
    }


    //配置spring Security的Filter链
    @Override
    public void configure(WebSecurity web) throws Exception {
        //不拦截对于resources目录下的请求
        web.ignoring().antMatchers("/resources/**","/webjars/**");
    }

    //PasswordEncoder是Spring官方提供的一个md5密码计米器，一般用于密码的加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    //该方法用于获取用户详细信息
    @Bean

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new JdbcUserDetails();
    }
}
