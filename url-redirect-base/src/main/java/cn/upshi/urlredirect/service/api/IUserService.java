package cn.upshi.urlredirect.service.api;

import cn.upshi.urlredirect.dto.UserDTO;
import cn.upshi.urlredirect.model.User;
import com.github.pagehelper.PageInfo;

/**
 * CloudPrint cn.edu.zju.cst.cloudprint.service.api
 * 描述：
 *
 * 时间：2016-12-8 13:43.
 */

public interface IUserService {

    public User dologin(String userid, String password);

    PageInfo<User> search(UserDTO userDTO, int page, int size, String order);

    int modifyStatus(String userid, Integer status);

    int updatePassword(String userid, String newPassword);

    User selectById(String userid);

    int addUser(User user);

}
