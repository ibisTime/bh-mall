package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSMenuAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.SYSMenu;
import com.bh.mall.dto.req.XN627052Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 菜单-修改
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:46:32 
 * @history:
 */
public class XN627052 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN627052Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu condition = new SYSMenu();
        condition.setCode(req.getCode());
        condition.setName(req.getName());
        condition.setType(req.getType());
        condition.setUrl(req.getUrl());
        condition.setParentCode(req.getParentCode());
        condition.setOrderNo(req.getOrderNo());

        condition.setUpdater(req.getUpdater());
        condition.setRemark(req.getRemark());
        return new BooleanRes(sysMenuAO.editSYSMenu(condition));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627052Req.class);
        ObjValidater.validateReq(req);
    }
}
