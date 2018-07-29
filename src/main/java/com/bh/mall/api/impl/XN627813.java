package com.bh.mall.api.impl;

import com.bh.mall.ao.IWareAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.dto.req.XN627813Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查云仓库
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627813 extends AProcessor {

    private IWareAO wareAO = SpringContextHolder
        .getBean(IWareAO.class);

    private XN627813Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return wareAO.getWare(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627813Req.class);
    }

}
