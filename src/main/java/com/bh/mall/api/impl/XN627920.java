package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSRoleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.dto.req.XN627920Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 角色-新增
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:15 
 * @history:
 */
public class XN627920 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN627920Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole data = new SYSRole();
        data.setName(req.getName());
        data.setLevel(req.getLevel());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setSystemCode(req.getSystemCode());
        return new PKCodeRes(sysRoleAO.addSYSRole(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627920Req.class);
        StringValidater.validateBlank(req.getName(), req.getLevel(),
            req.getUpdater(), req.getSystemCode());
    }

}
