package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.MiniCode;

public interface IMiniCodeDAO extends IBaseDAO<MiniCode> {
    String NAMESPACE = IMiniCodeDAO.class.getName().concat(".");

    void update(MiniCode data);

    void updateStatus(MiniCode data);

    List<MiniCode> selectCodeList(MiniCode condition);

    void updateNumber(MiniCode data);

}
