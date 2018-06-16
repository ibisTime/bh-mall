package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IChannelBankAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChannelBank;
import com.bh.mall.dto.req.XN627105Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查银行
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN627105 extends AProcessor {

    private IChannelBankAO channelBankAO = SpringContextHolder
        .getBean(IChannelBankAO.class);

    private XN627105Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ChannelBank condition = new ChannelBank();
        condition.setBankCode(req.getBankCode());
        condition.setBankName(req.getBankName());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IChannelBankAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return channelBankAO.queryChannelBankPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627105Req.class);
        ObjValidater.validateReq(req);
    }

}
