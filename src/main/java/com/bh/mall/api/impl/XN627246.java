package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IIntroAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.Intro;
import com.bh.mall.dto.req.XN627246Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询介绍奖励
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:33:35 
 * @history:
 */
public class XN627246 extends AProcessor {
    private IIntroAO introAO = SpringContextHolder.getBean(IIntroAO.class);

    private XN627246Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Intro condition = new Intro();
        condition.setCode(req.getCode());
        condition.setLevel(req.getLevel());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isNotBlank(orderColumn)) {
            orderColumn = IIntroAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return introAO.queryIntroList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627246Req.class);
    }

}
