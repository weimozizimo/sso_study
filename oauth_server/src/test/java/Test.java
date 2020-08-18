import boot.MainApplication;
import boot.dao.UserDao;
import boot.domain.Credentials;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
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
}
