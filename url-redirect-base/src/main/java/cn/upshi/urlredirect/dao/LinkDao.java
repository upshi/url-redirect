package cn.upshi.urlredirect.dao;

import cn.upshi.urlredirect.model.Link;

public interface LinkDao {
    int deleteByPrimaryKey(String uuid);

    int insert(Link record);

    int insertSelective(Link record);

    Link selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);

    Link selectByCode(String code);
}