package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN8051083ZReq;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 修改用户手机号和身份认证信息
 * @author: xieyj 
 * @since: 2016年12月15日 下午9:00:02 
 * @history:
 */
public class XN805083Z extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN8051083ZReq req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModfiyMobileAndIds(req.getUserId(), req.getMobile(),
            req.getRealName(), req.getIdKind(), req.getIdNo());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN8051083ZReq.class);
        StringValidater.validateBlank(req.getUserId(), req.getMobile(),
            req.getRealName(), req.getIdKind(), req.getIdNo());
    }
}
