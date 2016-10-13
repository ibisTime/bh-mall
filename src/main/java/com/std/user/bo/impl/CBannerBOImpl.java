package com.std.user.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.ICBannerBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.core.EGeneratePrefix;
import com.std.user.core.OrderNoGenerater;
import com.std.user.dao.ICBannerDAO;
import com.std.user.domain.CBanner;
import com.std.user.exception.BizException;

@Component
public class CBannerBOImpl extends PaginableBOImpl<CBanner> implements
        ICBannerBO {

    @Autowired
    private ICBannerDAO cBannerDAO;

    @Override
    public boolean isCBannerExist(String code) {
        CBanner condition = new CBanner();
        condition.setCode(code);
        if (cBannerDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveCBanner(CBanner data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.CB.getCode());
            data.setCode(code);
            data.setUpdateDatetime(new Date());
            cBannerDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeCBanner(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            CBanner data = new CBanner();
            data.setCode(code);
            count = cBannerDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCBanner(CBanner data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            data.setUpdateDatetime(new Date());
            count = cBannerDAO.update(data);
        }
        return count;
    }

    @Override
    public List<CBanner> queryCBannerList(CBanner condition) {
        return cBannerDAO.selectList(condition);
    }

    @Override
    public CBanner getCBanner(String code) {
        CBanner data = null;
        if (StringUtils.isNotBlank(code)) {
            CBanner condition = new CBanner();
            condition.setCode(code);
            data = cBannerDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该编号不存在");
            }
        }
        return data;
    }
}
