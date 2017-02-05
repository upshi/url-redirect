package cn.upshi.urlredirect.dao;

import cn.upshi.urlredirect.dto.UserDTO;
import cn.upshi.urlredirect.model.User;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByIdPwd(String userid, String password);

    List<User> search(UserDTO userDTO);

}