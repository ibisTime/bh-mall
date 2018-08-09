package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IMiniCodeAO;
import com.bh.mall.ao.IOutOrderAO;
import com.bh.mall.ao.IProCodeAO;
import com.bh.mall.bo.IMiniCodeBO;
import com.bh.mall.bo.IProCodeBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.MiniCode;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.exception.BizException;

@Service
public class MiniCodeAOImpl implements IMiniCodeAO {

    @Autowired
    IMiniCodeBO miniCodeBO;

    @Autowired
    IProCodeBO proCodeBO;

    @Autowired
    IProCodeAO proCodeAO;

    @Autowired
    IOutOrderAO outOrderAO;

    @Override
    public Paginable<MiniCode> queryMiniCodePage(int start, int limit,
            MiniCode condition) {
        return miniCodeBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<MiniCode> queryMiniCodeList(MiniCode condition) {
        return miniCodeBO.queryMiniCodeList(condition);
    }

    @Override
    public MiniCode getMiniCode(String code) {
        return miniCodeBO.getMiniCode(code);
    }

    @Override
    public int getSecurity(String miniCode) {
        MiniCode data = miniCodeBO.getSecurity(miniCode);
        if (!ECodeStatus.USE_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该防伪码还未启用");
        }

        // 查询次数是否为空，防止之前未默认为零的报错
        if (null == data.getNumber()) {
            data.setNumber(0);
        }
        return miniCodeBO.refreshSecurity(data);
    }

    @Override
    public MiniCode getTrace(String traceCode) {
        MiniCode data = miniCodeBO.getTrace(traceCode);
        if (!ECodeStatus.USE_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该溯源码还未启用");
        }
        if (StringUtils.isBlank(data.getOrderCode())) {
            throw new BizException("xn00000", "该溯源码还未绑定任何订单");
        }

        OutOrder inOrder = outOrderAO.getOutOrder(data.getOrderCode());
        data.setOutOrder(inOrder);
        return data;
    }
}
