package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IMiniCodeBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.DateUtil;
import com.bh.mall.dao.IMiniCodeDAO;
import com.bh.mall.domain.MiniCode;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.exception.BizException;

@Component
public class MiniCodeBOImpl extends PaginableBOImpl<MiniCode>
        implements IMiniCodeBO {

    @Autowired
    private IMiniCodeDAO miniCodeDAO;

    public void saveMiniCode(String miniCode, String traceCode, String proCode,
            Date date) {
        MiniCode data = new MiniCode();
        data.setMiniCode(miniCode);
        data.setTraceCode(traceCode);
        data.setRefCode(proCode);
        data.setStatus(ECodeStatus.TO_USER.getCode());

        data.setBatch(DateUtil.getToDay());
        data.setCreateDatetime(date);
        miniCodeDAO.insert(data);
    }

    @Override
    public List<MiniCode> getMiniCodeByProCode(String code) {
        MiniCode condition = new MiniCode();
        condition.setRefCode(code);
        List<MiniCode> list = miniCodeDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "该箱码已被使用啦！");
        }
        return list;
    }

    @Override
    public void refreshMiniCode(MiniCode data) {
        data.setStatus(ECodeStatus.USE_NO.getCode());
        miniCodeDAO.update(data);
    }

    @Override
    public List<MiniCode> queryMiniCodeList(MiniCode condition) {
        return miniCodeDAO.selectList(condition);
    }

    @Override
    public MiniCode getMiniCode(String code) {
        MiniCode data = null;
        if (StringUtils.isNotBlank(code)) {
            MiniCode condition = new MiniCode();
            data = miniCodeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "盒码不存在");
            }
        }
        return data;
    }

    @Override
    public List<MiniCode> queryCodeList() {
        MiniCode condition = new MiniCode();
        return miniCodeDAO.selectCodeList(condition);
    }

    @Override
    public void refreshStatus(MiniCode data, String orderCode) {
        data.setStatus(ECodeStatus.USE_YES.getCode());
        data.setOrderCode(orderCode);
        data.setUseDatetime(new Date());
        miniCodeDAO.updateStatus(data);

    }

    @Override
    public MiniCode getNoUseMiniCode() {
        MiniCode condition = new MiniCode();
        List<MiniCode> list = miniCodeDAO.selectCodeList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "盒码没有啦");
        }
        return list.get(0);
    }

    @Override
    public int refreshSecurity(MiniCode data) {
        data.setNumber(data.getNumber() + 1);
        miniCodeDAO.updateNumber(data);
        return data.getNumber();
    }

    @Override
    public MiniCode getTrace(String traceCode) {
        MiniCode data = null;
        if (StringUtils.isNotBlank(traceCode)) {
            MiniCode condition = new MiniCode();
            condition.setTraceCode(traceCode);
            data = miniCodeDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "防伪码不存在");
            }
        }
        return data;
    }

    @Override
    public MiniCode getSecurity(String miniCode) {
        MiniCode data = null;
        if (StringUtils.isNotBlank(miniCode)) {
            MiniCode condition = new MiniCode();
            condition.setMiniCode(miniCode);
            data = miniCodeDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "防伪码不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshStatusByProCode(String proCode, String orderCode) {
        MiniCode data = new MiniCode();
        data.setStatus(ECodeStatus.USE_YES.getCode());
        data.setOrderCode(orderCode);
        data.setUseDatetime(new Date());
        data.setRefCode(proCode);
        miniCodeDAO.updateStatusByProCode(data);
    }

}
