//package boot.config;
//
//import boot.dao.UserDao;
//import boot.domain.Credentials;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.sql.SQLException;
//
//public class JdbcUserDetails implements UserDetailsService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        if(credentials==null){
//            throw new UsernameNotFoundException("User"+username+"can not be found");
//        }
//
//        new User(credentials.getName(),credentials.getPassword(),credentials.getEnabled(),true,true,true,credentials.);
//    }
//}
