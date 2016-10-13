package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICPasswordAO;
import com.std.user.bo.ICPasswordBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.CPassword;
import com.std.user.exception.BizException;

@Service
public class CPasswordAOImpl implements ICPasswordAO {

    @Autowired
    private ICPasswordBO cPasswordBO;

    @Override
    public String addCPassword(CPassword data) {
        return cPasswordBO.saveCPassword(data);
    }

    @Override
    public int editCPassword(CPassword data) {
        if (!cPasswordBO.isCPasswordExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cPasswordBO.refreshCPassword(data);
    }

    @Override
    public int dropCPassword(String code) {
        if (!cPasswordBO.isCPasswordExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cPasswordBO.removeCPassword(code);
    }

    @Override
    public Paginable<CPassword> queryCPasswordPage(int start, int limit,
            CPassword condition) {
        return cPasswordBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CPassword> queryCPasswordList(CPassword condition) {
        return cPasswordBO.queryCPasswordList(condition);
    }

    @Override
    public CPassword getCPassword(String code) {
        return cPasswordBO.getCPassword(code);
    }
}
