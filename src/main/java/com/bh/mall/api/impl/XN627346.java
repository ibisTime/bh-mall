package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ICUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.CUser;
import com.bh.mall.dto.req.XN627346Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询C端用户
 * @author: nyc 
 * @since: 2018年8月11日 下午6:34:30 
 * @history:
 */
public class XN627346 extends AProcessor {
    private ICUserAO userAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN627346Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CUser condition = new CUser();
        condition.setNickname(req.getNickname());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return userAO.queryCuserList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627346Req.class);
    }
}
