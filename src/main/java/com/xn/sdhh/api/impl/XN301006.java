package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSRoleAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.domain.SYSRole;
import com.xn.sdhh.dto.req.XN301006Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 角色-列表查询
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:24:46 
 * @history:
 */
public class XN301006 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN301006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole condition = new SYSRole();
        condition.setName(req.getName());
        condition.setUpdater(req.getUpdater());
        condition.setSystemCode(req.getSystemCode());
        return sysRoleAO.querySYSRoleList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN301006Req.class);
        ObjValidater.validateReq(req);
    }
}
