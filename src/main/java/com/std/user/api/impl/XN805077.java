package com.std.user.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.User;
import com.std.user.dto.req.XN805077Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 按地方分页查询用户
 * @author: xieyj 
 * @since: 2016年10月11日 下午11:14:11 
 * @history:
 */
public class XN805077 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805077Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        User condition = new User();
        condition.setLoginNameForLikeQuery(req.getLoginName());
        condition.setNicknameForLikeQuery(req.getNickname());
        condition.setLevel(req.getLevel());
        condition.setMobileForLikeQuery(req.getMobile());

        condition.setOssUserId(req.getOssUserId());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return userAO.queryAreaUserPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805077Req.class);
        StringValidater.validateBlank(req.getOssUserId());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
