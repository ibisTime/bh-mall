package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSConfigAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627967Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 根据key获取value值
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:56:04 
 * @history:
 */
public class XN627967 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN627967Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return sysConfigAO.getSYSConfig(req.getCkey(), req.getCompanyCode(),
            req.getSystemCode());
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627967Req.class);
        StringValidater.validateBlank(req.getCkey(), req.getCompanyCode(),
            req.getSystemCode());
    }

}
