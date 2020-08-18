package boot.config;

import boot.domain.Credentials;
import boot.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLException;

public class JdbcUserDetails implements UserDetailsService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = null;
        try {
            credentials = credentialRepository.findByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(credentials==null){
            throw new UsernameNotFoundException("User"+username+"can not be found");
        }

        new User(credentials.getName(),credentials.getPassword(),credentials.getEnabled(),true,true,true,credentials.);
    }
}
