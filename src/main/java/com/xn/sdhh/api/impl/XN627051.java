package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSMenuAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627051Req;
import com.xn.sdhh.dto.res.BooleanRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 菜单-删除
 * @author: xieyj 
 * @since: 2016年5月16日 下午11:00:43 
 * @history:
 */
public class XN627051 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN627051Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysMenuAO.dropSYSMenu(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627051Req.class);
        ObjValidater.validateReq(req);
    }
}
