package com.std.user.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.user.ao.IBlacklistAO;
import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.Blacklist;
import com.std.user.dto.req.XN805245Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 分页查询黑名单
 * @author: xieyj 
 * @since: 2017年9月12日 下午4:20:09 
 * @history:
 */
public class XN805245 extends AProcessor {
    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805245Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Blacklist condition = new Blacklist();
        condition.setUserId(req.getUserId());
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setSystemCode(req.getSystemCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return blacklistAO.queryBlacklistPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805245Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getSystemCode());
    }
}
