package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSConfigAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.domain.SYSConfig;
import com.xn.sdhh.dto.req.XN627081Req;
import com.xn.sdhh.dto.res.BooleanRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 修改系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:54:21 
 * @history:
 */
public class XN627081 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN627081Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSConfig data = new SYSConfig();
        data.setId(req.getId());
        data.setCvalue(req.getCvalue());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        sysConfigAO.editSYSConfig(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627081Req.class);
        ObjValidater.validateReq(req);
    }

}
