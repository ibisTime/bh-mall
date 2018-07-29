package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IProCodeBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IProCodeDAO;
import com.bh.mall.domain.ProCode;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.exception.BizException;

@Component
public class ProCodeBOImpl extends PaginableBOImpl<ProCode>
        implements IProCodeBO {

    @Autowired
    private IProCodeDAO proCodeDAO;

    public void saveProCode(ProCode data) {
        proCodeDAO.insert(data);

    }

    @Override
    public void refreshProCode(ProCode data) {
        data.setStatus(ECodeStatus.USE_YES.getCode());
        data.setUseDatetime(new Date());
        proCodeDAO.update(data);
    }

    @Override
    public List<ProCode> queryProCodeList(ProCode condition) {
        return proCodeDAO.selectList(condition);
    }

    @Override
    public ProCode getProCode(String code) {
        ProCode data = null;
        if (StringUtils.isNotBlank(code)) {
            ProCode condition = new ProCode();
            condition.setCode(code);
            data = proCodeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "条形码不存在");
            }
        }
        return data;
    }

    @Override
    public boolean checkCode(String code) {
        ProCode data = null;
        if (StringUtils.isNotBlank(code)) {
            ProCode condition = new ProCode();
            condition.setCode(code);
            data = proCodeDAO.select(condition);
            if (data == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ProCode getNoUseProCode() {
        ProCode condition = new ProCode();
        condition.setStatus(EBoolean.NO.getCode());
        List<ProCode> list = proCodeDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "箱码已全部用完，请及时补充箱码！");
        }
        return list.get(0);
    }

    @Override
    public List<ProCode> queryCodeList() {
        ProCode condition = new ProCode();
        return proCodeDAO.selectCodeList(condition);
    }

    @Override
    public void splitSingle(ProCode barData) {
        barData.setStatus(ECodeStatus.SPLIT_SINGLE.getCode());
        barData.setUseDatetime(new Date());
        proCodeDAO.splitSingle(barData);
    }

}
