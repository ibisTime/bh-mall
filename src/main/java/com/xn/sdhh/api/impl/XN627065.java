package com.xn.sdhh.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.xn.sdhh.ao.ISYSMenuAO;
import com.xn.sdhh.ao.ISYSMenuRoleAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.domain.SYSMenuRole;
import com.xn.sdhh.dto.req.XN627065Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 菜单角色-查询菜单列表
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN627065 extends AProcessor {
    private ISYSMenuRoleAO sysMenuRoleAO = SpringContextHolder
        .getBean(ISYSMenuRoleAO.class);

    private XN627065Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenuRole data = new SYSMenuRole();
        data.setRoleCode(req.getRoleCode());
        data.setParentCode(req.getParentCode());
        data.setType(req.getType());
        data.setSystemCode(req.getSystemCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSMenuAO.DEFAULT_ORDER_COLUMN;
        }
        data.setOrder(column, req.getOrderDir());

        return sysMenuRoleAO.querySYSMenuRoleList(data);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627065Req.class);
        ObjValidater.validateReq(req);
    }
}
