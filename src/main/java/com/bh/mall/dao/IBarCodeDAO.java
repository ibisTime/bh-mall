package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.BarCode;

//dao层 
public interface IBarCodeDAO extends IBaseDAO<BarCode> {

    String NAMESPACE = IBarCodeDAO.class.getName().concat(".");

    void update(BarCode data);

    List<BarCode> selectCodeList(BarCode condition);

}
