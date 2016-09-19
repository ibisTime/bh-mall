package com.std.user.api.impl;

import com.std.user.ao.IUserExtAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805074Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 更改用户扩展信息
 * @author: zuixian 
 * @since: 2016年9月19日 下午1:33:07 
 * @history:
 */
public class XN805074 extends AProcessor {

    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN805074Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userExtAO.refreshUserExt(req.getUserId(), req.getGender(),
            req.getBirthday(), req.getRegion(), req.getIntroduce());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805074Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
