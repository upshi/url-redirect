package cn.upshi.urlredirect.service.impl;

import cn.upshi.urlredirect.dao.LinkDao;
import cn.upshi.urlredirect.model.Link;
import cn.upshi.urlredirect.service.api.ILinkService;
import cn.upshi.urlredirect.util.RandomUtil;
import cn.upshi.urlredirect.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * url-redirect cn.upshi.urlredirect.service.impl
 * 描述：
 * 时间：2017-2-5 22:08.
 */

@Service
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private LinkDao linkDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Link selectByCode(String code) {
        return linkDao.selectByCode(code);
    }

    @Override
    public Link selectByUrl(String longUrl) {
        return linkDao.selectByUrl(longUrl);
    }

    @Override
    public String add(String name, String longUrl) {
        String code = RandomUtil.digitCharString(6);
        Link l = linkDao.selectByCode(code);
        while(l != null) {
            code = RandomUtil.digitCharString(6);
            l = linkDao.selectByCode(code);
        }

        Link link = new Link();
        link.setUuid(StringUtil.uuid());
        link.setName(name);
        link.setCode(code);
        link.setUrl(longUrl);
        link.setTime(sdf.format(new Date()));
        int i = linkDao.insert(link);
        if(i > 0) {
            return code;
        } else {
            return null;
        }
    }
}
