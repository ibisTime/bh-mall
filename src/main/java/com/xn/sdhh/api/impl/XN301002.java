package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSRoleAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.StringValidater;
import com.xn.sdhh.dto.req.XN301002Req;
import com.xn.sdhh.dto.res.BooleanRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 角色-删除
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:51 
 * @history:
 */
public class XN301002 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN301002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(sysRoleAO.dropSYSRole(req.getCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN301002Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
