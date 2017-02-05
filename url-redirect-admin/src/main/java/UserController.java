package cn.upshi.urlredirect.controller;


import cn.upshi.urlredirect.dto.UserDTO;
import cn.upshi.urlredirect.model.User;
import cn.upshi.urlredirect.service.api.IUserService;
import cn.upshi.urlredirect.util.Base64Util;
import cn.upshi.urlredirect.util.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * CloudPrint cn.upshi.urlredirect.controller
 * 描述：
 * <p>
 * 时间：2016-12-8 14:34.
 */

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping(value = "/admin/user/search")
    public Map<String, Object> search(UserDTO userDTO,
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "userid desc") String order) {
        Map<String, Object> dataMap = new HashMap<String, Object>();

        PageInfo<User> pageInfo = userService.search(userDTO, page, size, order);
        dataMap.put("users", pageInfo.getList());
        dataMap.put("page", page);
        dataMap.put("size", pageInfo.getSize());
        dataMap.put("totals", pageInfo.getTotal());
        return RespUtil.success(dataMap);
    }

    @RequestMapping(value = "/admin/user/modifyStatus")
    public Map<String, Object> modifyStatus(String userid, Integer status) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        userService.modifyStatus(userid, status);
        return RespUtil.success(dataMap);
    }

    @RequestMapping(value = "/admin/user/checkUserid")
    public Map<String, Object> checkUserid(String userid) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = userService.selectById(userid);
        dataMap.put("exist", user != null);
        return RespUtil.success(dataMap);
    }

    @RequestMapping(value = "/admin/user/addUser")
    public Map<String, Object> addUser(User user) {
        user.setStatus(1);
        user.setPassword(encoder.encode(user.getPassword()));
        int i = userService.addUser(user);
        if (i != 0) {
            return RespUtil.success(null);
        } else {
            return RespUtil.error("添加用户失败!");
        }
    }

    @RequestMapping(value = "/user/personalInfo")
    public Map<String, Object> personalInfo(HttpSession session) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        dataMap.put("user", user);
        return RespUtil.success(dataMap);
    }

    @RequestMapping(value = "/user/checkPassword")
    public Map<String, Object> checkPassword(String password, HttpSession session) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        boolean matches = encoder.matches(Base64Util.decode(password), user.getPassword());
        dataMap.put("correct", matches);
        return RespUtil.success(dataMap);
    }

    @RequestMapping(value = "/user/updatePassword")
    public Map<String, Object> updatePassword(String oldPassword, String newPassword, HttpSession session) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        boolean matches = encoder.matches(Base64Util.decode(oldPassword), user.getPassword());
        if (matches) {
            int i = userService.updatePassword(user.getUserid(), newPassword);
            if (i > 0) {
                user.setPassword(encoder.encode(Base64Util.decode(newPassword)));
                session.setAttribute("user", user);
                dataMap.put("role", user.getRole());
                return RespUtil.success(dataMap);
            } else {
                return RespUtil.error("请稍后再试");
            }
        } else {
            return RespUtil.error("请稍后再试");
        }
    }
}
