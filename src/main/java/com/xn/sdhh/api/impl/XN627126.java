package com.xn.sdhh.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.domain.SYSUser;
import com.xn.sdhh.dto.req.XN627126Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 分页查询用户
 * @author: chenshan 
 * @since: 2018年3月26日 下午6:28:36 
 * @history:
 */
public class XN627126 extends AProcessor {

    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN627126Req req = null;

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

        return sysUserAO.querySYSUserList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627126Req.class);
    }

}
