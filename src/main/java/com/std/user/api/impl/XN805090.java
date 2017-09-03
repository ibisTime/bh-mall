package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805090Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 经纬度定位
 * @author: xieyj 
 * @since: 2016年12月15日 下午9:00:02 
 * @history:
 */
public class XN805090 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805090Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyLngLat(req.getUserId(), req.getLongitude(),
            req.getLatitude());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805090Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getLongitude(),
            req.getLatitude());
    }
}
