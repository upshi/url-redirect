package cn.upshi.urlredirect.service.impl;

import cn.upshi.urlredirect.dao.LinkDao;
import cn.upshi.urlredirect.model.Link;
import cn.upshi.urlredirect.service.api.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * url-redirect cn.upshi.urlredirect.service.impl
 * 描述：
 * 时间：2017-2-5 22:08.
 */

@Service
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private LinkDao linkDao;

    @Override
    public Link getByCode(String code) {
        return linkDao.selectByCode(code);
    }
}
