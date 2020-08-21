import boot.MainApplication;
import boot.dao.UserDao;
import boot.domain.Authority;
import boot.domain.Credentials;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//此处参数为正式启动类中的类对象
@SpringBootTest(classes = MainApplication.class)
@EnableAutoConfiguration
public class Test {
    @Autowired
    UserDao userDao;
    @org.junit.Test
    public void test1(){
        Credentials admin = userDao.getUserByName("oauth_admin");
        System.out.println(admin);
    }


    @org.junit.Test
    public void test2(){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String client1 = encoder.encode("client_1");
//        System.out.println(client1);
    }
}
