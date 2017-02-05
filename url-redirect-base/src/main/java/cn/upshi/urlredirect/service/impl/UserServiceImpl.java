package cn.upshi.urlredirect.service.impl;

import cn.upshi.urlredirect.dao.UserDao;
import cn.upshi.urlredirect.dto.UserDTO;
import cn.upshi.urlredirect.model.User;
import cn.upshi.urlredirect.service.api.IUserService;
import cn.upshi.urlredirect.util.Base64Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * CloudPrint cn.upshi.urlredirect.service.impl
 * 描述：
 *
 * 时间：2016-12-8 13:50.
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User dologin(String userid, String password){
        User user = userDao.selectByPrimaryKey(userid);
        if (user == null) {
            return null;
        }
        boolean match = encoder.matches(password, user.getPassword());
        if (match) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public PageInfo<User> search(UserDTO userDTO, int page, int size, String order) {
        PageHelper.startPage(page, size, order);
        List<User> dataSources = userDao.search(userDTO);
        PageInfo<User> pageInfo = new PageInfo<User>(dataSources);
        return pageInfo;
    }

    @Override
    public int modifyStatus(String userid, Integer status) {
        User user = new User();
        user.setUserid(userid);
        user.setStatus(status);
        return userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updatePassword(String userid, String newPassword) {User user = new User();
        user.setUserid(userid);
        user.setPassword(encoder.encode(Base64Util.decode(newPassword)));
        return userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectById(String userid) {
        return userDao.selectByPrimaryKey(userid);
    }

    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

}
