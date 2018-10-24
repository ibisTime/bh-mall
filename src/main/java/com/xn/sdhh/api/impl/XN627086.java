package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSConfigAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.StringValidater;
import com.xn.sdhh.dto.req.XN627086Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 详情查询系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:55:26 
 * @history:
 */
public class XN627086 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN627086Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return sysConfigAO.getSYSConfig(StringValidater.toLong(req.getId()));
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627086Req.class);
        StringValidater.validateBlank(req.getId());
    }

}
