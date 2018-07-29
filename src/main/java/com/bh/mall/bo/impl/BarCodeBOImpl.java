package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IBarCodeBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IBarCodeDAO;
import com.bh.mall.domain.BarCode;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.exception.BizException;

@Component
public class BarCodeBOImpl extends PaginableBOImpl<BarCode>
        implements IBarCodeBO {

    @Autowired
    private IBarCodeDAO barCodeDAO;

    public void saveBarCode(BarCode data) {
        barCodeDAO.insert(data);

    }

    @Override
    public void refreshBarCode(BarCode data) {
        data.setStatus(ECodeStatus.USE_NO.getCode());
        data.setUseDatetime(new Date());
        barCodeDAO.update(data);
    }

    @Override
    public List<BarCode> queryBarCodeList(BarCode condition) {
        return barCodeDAO.selectList(condition);
    }

    @Override
    public BarCode getBarCode(String code) {
        BarCode data = null;
        if (StringUtils.isNotBlank(code)) {
            BarCode condition = new BarCode();
            condition.setCode(code);
            data = barCodeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "条形码不存在");
            }
        }
        return data;
    }

    @Override
    public boolean checkCode(String code) {
        BarCode data = null;
        if (StringUtils.isNotBlank(code)) {
            BarCode condition = new BarCode();
            condition.setCode(code);
            data = barCodeDAO.select(condition);
            if (data == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public BarCode getNoUseBarCode() {
        BarCode condition = new BarCode();
        condition.setStatus(ECodeStatus.USE_NO.getCode());
        List<BarCode> list = barCodeDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "箱码已全部用完，请及时补充箱码！");
        }
        return list.get(0);
    }

    @Override
    public List<BarCode> queryCodeList() {
        BarCode condition = new BarCode();
        return barCodeDAO.selectCodeList(condition);
    }

    @Override
    public void splitSingle(BarCode barData) {
        barData.setStatus(ECodeStatus.SPLIT_SINGLE.getCode());
        barData.setUseDatetime(new Date());
        barCodeDAO.splitSingle(barData);
    }

}
