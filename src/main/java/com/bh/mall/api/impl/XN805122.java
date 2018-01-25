package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805122Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 推荐人查询用户列表
 * @author: xieyj 
 * @since: 2016年12月15日 下午9:00:02 
 * @history:
 */
public class XN805122 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805122Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.getUserRefereeList(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805122Req.class);
        StringValidater.validateBlank(req.getUserId());
    }
}
