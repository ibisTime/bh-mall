package com.std.user.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.user.ao.IAuthLogAO;
import com.std.user.api.AProcessor;
import com.std.user.common.DateUtil;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.AuthLog;
import com.std.user.dto.req.XN805215Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 分页查询认证记录
 * @author: xieyj 
 * @since: 2017年9月10日 上午10:24:10 
 * @history:
 */
public class XN805215 extends AProcessor {
    private IAuthLogAO authLogAO = SpringContextHolder
        .getBean(IAuthLogAO.class);

    private XN805215Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AuthLog condition = new AuthLog();
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getApplyUser());
        condition.setApplyDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setApplyDatetimeEnd(DateUtil.getFrontDate(req.getDateStart(),
            true));

        condition.setApproveUser(req.getApproveUser());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setSystemCode(req.getSystemCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAuthLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return authLogAO.queryAuthLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805215Req.class);
        StringValidater
            .validateBlank(req.getCompanyCode(), req.getSystemCode());
    }
}
