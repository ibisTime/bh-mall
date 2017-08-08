package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805069Req;
import com.std.user.dto.res.XN805051Res;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 修改支付密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:30:11 
 * @history:
 */
public class XN805069 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805069Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyTradePwd(req.getUserId(), req.getOldTradePwd(),
            req.getNewTradePwd());
        return new XN805051Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805069Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getOldTradePwd(),
            req.getNewTradePwd());

    }

}
