package boot.config;

import boot.dao.UserDao;
import boot.domain.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLException;

/**
*@description 实现用户详细信息服务接口，来通过调用数据库的方式获取用户权限信息
*@author weiyifei
*@date 2020/8/21
*/
public class JdbcUserDetails implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Credentials credentials = userDao.getUserByName(username);

        if(credentials==null){
            throw new UsernameNotFoundException("User"+username+"can not be found");
        }

        User user = new User(credentials.getName(), credentials.getPassword(), credentials.isEnabled(), true, true, true, credentials.getAuthorities());

        return user;
    }
}
