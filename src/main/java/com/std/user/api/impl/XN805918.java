package com.std.user.api.impl;

import com.std.user.ao.ISYSConfigAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805918Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 根据类型查询系统参数
 * @author: xieyj 
 * @since: 2017年8月23日 上午10:03:19 
 * @history:
 */
public class XN805918 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN805918Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return sysConfigAO.querySYSConfig(req.getType(), req.getCompanyCode(),
            req.getSystemCode());
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805918Req.class);
        StringValidater.validateBlank(req.getType(), req.getCompanyCode(),
            req.getSystemCode());
    }

}
