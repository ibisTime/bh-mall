package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IProCodeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.ProCode;
import com.bh.mall.dto.req.XN627885Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询箱码
 * @author: nyc 
 * @since: 2018年7月13日 下午5:47:52 
 * @history:
 */

public class XN627896 extends AProcessor {

    private IProCodeAO proCodeAO = SpringContextHolder
        .getBean(IProCodeAO.class);

    private XN627885Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProCode condition = new ProCode();
        condition.setStatusList(req.getStatusList());
        condition.setKeyword(req.getKeyword());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProCodeAO.DEFAULT_ORDER_COLUMN;
        }

        return proCodeAO.queryProCodeList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627885Req.class);
    }

}
