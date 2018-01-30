package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSRoleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627921Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 角色-删除
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:51 
 * @history:
 */
public class XN627921 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN627921Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(sysRoleAO.dropSYSRole(req.getCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627921Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
