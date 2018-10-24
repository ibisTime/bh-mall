package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSConfigAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627087Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 根据key获取value值
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:56:04 
 * @history:
 */
public class XN627087 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN627087Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysConfigAO.getSYSConfig(req.getCkey());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627087Req.class);
        ObjValidater.validateReq(req);
    }

}
