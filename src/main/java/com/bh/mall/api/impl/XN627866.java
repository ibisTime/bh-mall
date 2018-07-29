package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IChAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChAward;
import com.bh.mall.dto.req.XN627866Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询介绍奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:55:07 
 * @history:
 */
public class XN627866 extends AProcessor {

    private IChAwardAO chAwardAO = SpringContextHolder
        .getBean(IChAwardAO.class);

    private XN627866Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ChAward condition = new ChAward();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IChAwardAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return chAwardAO.queryChAwardList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627866Req.class);
    }

}
