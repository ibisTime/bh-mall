package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.User;
import com.std.user.dto.req.XN805180Req;
import com.std.user.dto.res.XN805180Res;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 合伙人代注册
 * @author: xieyj 
 * @since: 2016年12月27日 下午3:16:40 
 * @history:
 */
public class XN805180 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805180Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        User data = new User();
        data.setLoginName(req.getLoginName());
        data.setMobile(req.getMobile());
        data.setIdKind(req.getIdKind());
        data.setIdNo(req.getIdNo());
        data.setRealName(req.getRealName());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setSystemCode(req.getSystemCode());
        return new XN805180Res(userAO.doAddPartner(data, req.getProvince(),
            req.getCity(), req.getArea()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805180Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getMobile(),
            req.getIdKind(), req.getIdNo(), req.getRealName(),
            req.getProvince(), req.getUpdater(), req.getSystemCode());
    }
}
