package cn.upshi.urlredirect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * url-redirect cn.upshi.urlredirect.controller
 * 描述：
 * 时间：2017-2-6 20:27.
 */

@Controller
public class CommonController {


    @GetMapping("/")
    public String redirect() {
        return "index";
    }


}
