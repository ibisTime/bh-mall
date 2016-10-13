package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.IUReadAO;
import com.std.user.bo.IB2cSmsBO;
import com.std.user.bo.IUReadBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.B2cSms;
import com.std.user.domain.URead;
import com.std.user.exception.BizException;

@Service
public class UReadAOImpl implements IUReadAO {

    @Autowired
    private IUReadBO uReadBO;

    @Autowired
    private IB2cSmsBO b2cSmsBO;

    @Override
    public String addURead(URead data) {
        return uReadBO.saveURead(data);
    }

    @Override
    public int editURead(URead data) {
        if (!uReadBO.isUReadExist(data.getId())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return uReadBO.refreshURead(data);
    }

    @Override
    public Paginable<URead> queryUReadPage(int start, int limit, URead condition) {
        Paginable<URead> uReadPage = uReadBO.getPaginable(start, limit,
            condition);
        List<URead> uReadList = uReadPage.getList();
        B2cSms b2cSms = new B2cSms();
        for (URead uRead : uReadList) {
            b2cSms = b2cSmsBO.getB2cSms(uRead.getSmsCode());
            uRead.setB2cSms(b2cSms);
        }
        return uReadPage;
    }

    @Override
    public List<URead> queryUReadList(URead condition) {
        return uReadBO.queryUReadList(condition);
    }

    @Override
    public URead getURead(Integer id) {
        URead uRead = uReadBO.getURead(id);
        B2cSms b2cSms = b2cSmsBO.getB2cSms(uRead.getSmsCode());
        uRead.setB2cSms(b2cSms);
        return uRead;
    }
}
