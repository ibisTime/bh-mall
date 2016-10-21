package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICMenuAO;
import com.std.user.bo.ICMenuBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.CMenu;
import com.std.user.enums.ECNavigateStatus;
import com.std.user.exception.BizException;

@Service
public class CMenuAOImpl implements ICMenuAO {

    @Autowired
    private ICMenuBO cMenuBO;

    @Override
    public String addCMenu(CMenu data) {
        return cMenuBO.saveCMenu(data);
    }

    @Override
    public int editCMenu(CMenu data) {
        if (!cMenuBO.isCMenuExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cMenuBO.refreshCMenu(data);
    }

    @Override
    public int dropCMenu(String code) {
        if (!cMenuBO.isCMenuExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cMenuBO.removeCMenu(code);
    }

    @Override
    public Paginable<CMenu> queryCMenuPage(int start, int limit, CMenu condition) {
        return cMenuBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CMenu> queryCMenuList(CMenu condition) {
        return cMenuBO.queryCMenuList(condition);
    }

    @Override
    public CMenu getCMenu(String code) {
        return cMenuBO.getCMenu(code);
    }

    @Override
    public int editCMenuStatus(String code) {
        if (!cMenuBO.isCMenuExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        CMenu menu = cMenuBO.getCMenu(code);
        CMenu data = new CMenu();
        data.setCode(code);
        if (ECNavigateStatus.APPROVE_NO.getCode()
            .equalsIgnoreCase(menu.getStatus())) {
            data.setStatus(ECNavigateStatus.APPROVE_YES.getCode());
        } else {
            data.setStatus(ECNavigateStatus.APPROVE_NO.getCode());
        }
        return cMenuBO.refreshCMenuStatus(data);
    }
}
