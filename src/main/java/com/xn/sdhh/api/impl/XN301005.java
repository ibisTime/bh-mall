package com.xn.sdhh.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.xn.sdhh.ao.ISYSRoleAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.core.StringValidater;
import com.xn.sdhh.domain.SYSRole;
import com.xn.sdhh.dto.req.XN301005Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 角色-分页查询
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:23:16 
 * @history:
 */
public class XN301005 extends AProcessor {

    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN301005Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole condition = new SYSRole();
        condition.setName(req.getName());

        condition.setUpdater(req.getUpdater());
        condition.setSystemCode(req.getSystemCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSRoleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return sysRoleAO.querySYSRolePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN301005Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        ObjValidater.validateReq(req);
    }
}
