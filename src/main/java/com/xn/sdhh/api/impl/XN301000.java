package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSRoleAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN301000Req;
import com.xn.sdhh.dto.res.PKCodeRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 角色-新增
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:15 
 * @history:
 */
public class XN301000 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN301000Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(sysRoleAO.addSYSRole(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN301000Req.class);
        ObjValidater.validateReq(req);
    }

}
