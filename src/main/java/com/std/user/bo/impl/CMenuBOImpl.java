package com.std.user.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.ICMenuBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.core.EGeneratePrefix;
import com.std.user.core.OrderNoGenerater;
import com.std.user.dao.ICMenuDAO;
import com.std.user.domain.CMenu;
import com.std.user.exception.BizException;

@Component
public class CMenuBOImpl extends PaginableBOImpl<CMenu> implements ICMenuBO {

    @Autowired
    private ICMenuDAO cMenuDAO;

    @Override
    public boolean isCMenuExist(String code) {
        CMenu condition = new CMenu();
        condition.setCode(code);
        if (cMenuDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveCMenu(CMenu data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.CM.getCode());
            data.setCode(code);
            cMenuDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeCMenu(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            CMenu data = new CMenu();
            data.setCode(code);
            count = cMenuDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCMenu(CMenu data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = cMenuDAO.update(data);
        }
        return count;
    }

    @Override
    public List<CMenu> queryCMenuList(CMenu condition) {
        return cMenuDAO.selectList(condition);
    }

    @Override
    public CMenu getCMenu(String code) {
        CMenu data = null;
        if (StringUtils.isNotBlank(code)) {
            CMenu condition = new CMenu();
            condition.setCode(code);
            data = cMenuDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该编号不存在");
            }
        }
        return data;
    }

    @Override
    public int refreshCMenuStatus(CMenu data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = cMenuDAO.updateStatus(data);
        }
        return count;
    }
}
