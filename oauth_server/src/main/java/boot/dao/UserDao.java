package boot.dao;

import boot.domain.Credentials;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    Credentials getUserByName(@Param("username")String username);
}
