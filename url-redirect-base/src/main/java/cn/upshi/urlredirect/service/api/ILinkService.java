package cn.upshi.urlredirect.service.api;

import cn.upshi.urlredirect.model.Link;

/**
 * url-redirect cn.upshi.urlredirect.service.api
 * 描述：
 * 时间：2017-2-5 22:08.
 */

public interface ILinkService {

    Link selectByCode(String code);

    Link selectByUrl(String longUrl);

    String add(String name, String longUrl);
}
