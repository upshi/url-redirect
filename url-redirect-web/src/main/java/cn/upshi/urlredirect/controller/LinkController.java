package cn.upshi.urlredirect.controller;

import cn.upshi.urlredirect.model.Link;
import cn.upshi.urlredirect.service.api.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * url-redirect cn.upshi.urlredirect.controller
 * 描述：
 * 时间：2017-2-5 16:23.
 */

@Controller
public class LinkController {

    @Autowired
    private ILinkService linkService;

    @GetMapping("/{code}")
    public void redirect(@PathVariable("code")String code, HttpServletResponse response) {
        Link link = linkService.getByCode(code);
        if(link != null) {
            String url = link.getUrl();
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    @GetMapping("/")
    public String redirect() {
        return "index";
    }


}
