package cn.upshi.urlredirect.controller;

import cn.upshi.urlredirect.model.Link;
import cn.upshi.urlredirect.service.api.ILinkService;
import cn.upshi.urlredirect.util.Base64Util;
import cn.upshi.urlredirect.util.RespUtil;
import cn.upshi.urlredirect.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        Link link = linkService.selectByCode(code);
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

    @ResponseBody
    @PostMapping("/dlj")
    public Map<String, Object> dlj(String name, String url) {
        Map<String, Object> dataMap = new HashMap<>();
        String longUrl = Base64Util.decode(url).trim();
        Link link = linkService.selectByUrl(longUrl);
        if( link != null ) {
            dataMap.put("code", link.getCode());
            return RespUtil.success(dataMap);
        } else {
            if(!longUrl.startsWith("http://") &&  !longUrl.startsWith("https://")) {
                longUrl += "http";
            }
            if(StringUtil.isEmpty(name)) {
                name = "无";
            }
            String code = linkService.add(name, longUrl);
            if( code != null ) {
                dataMap.put("code", code);
                return RespUtil.success(dataMap);
            } else {
                return RespUtil.error("请再试一遍");
            }
        }
    }

}
