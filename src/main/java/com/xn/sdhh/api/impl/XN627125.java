package com.xn.sdhh.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.core.StringValidater;
import com.xn.sdhh.domain.SYSUser;
import com.xn.sdhh.dto.req.XN627125Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 分页查询系统用户
 * @author: clockorange 
 * @since: Aug 7, 2018 10:05:42 AM 
 * @history:
 */
public class XN627125 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN627125Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSUser condition = new SYSUser();
        condition.setRealName(req.getRealName());
        condition.setRoleCode(req.getRoleCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return userAO.querySYSUserPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627125Req.class);
        ObjValidater.validateReq(req);
    }

}
