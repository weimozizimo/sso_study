package boot.repository;


import boot.domain.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Service
public class CredentialRepository {
    @Autowired
    DataSource dataSource;

    public Credentials findByName(String username) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        Credentials credentials = new Credentials();
        try{
            ResultSet resultSet = statement.executeQuery("SELECT\n" +
                    "credentials.id,\n" +
                    "credentials.enabled,\n" +
                    "credentials.`name`,\n" +
                    "credentials.`password`,\n" +
                    "credentials.version\n" +
                    "FROM\n" +
                    "credentials\n");

            credentials.setId(resultSet.getString(0));
            credentials.setEnabled(resultSet.getInt(1));
            credentials.setName(resultSet.getString(2));
            credentials.setPassword(resultSet.getString(3));
            credentials.setVersion(resultSet.getInt(4));
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.rollback();
            connection.close();
        }
        return credentials;
    }
}
