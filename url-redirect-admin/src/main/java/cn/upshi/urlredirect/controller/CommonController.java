package cn.upshi.urlredirect.controller;

import cn.upshi.urlredirect.model.User;
import cn.upshi.urlredirect.service.api.IUserService;
import cn.upshi.urlredirect.util.Base64Util;
import cn.upshi.urlredirect.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lijing on 2016/12/19.
 */

@RestController
public class CommonController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login")
    public Map<String, Object> login(String userid, String password, HttpSession session) {
        User user = userService.dologin(userid, Base64Util.decode(password));
        if (user != null) {
            session.setAttribute("user",user);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("user", user.toMap());
            return RespUtil.success(dataMap);
        } else {
            return RespUtil.error("用户名或密码错误");
        }
    }
}
