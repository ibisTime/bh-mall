package com.std.user.api.impl;

import com.std.user.ao.ICBannerAO;
import com.std.user.api.AProcessor;
import com.std.user.api.converter.CBannerConverter;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.CBanner;
import com.std.user.dto.req.XN806040Req;
import com.std.user.dto.res.PKCodeRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 新增Banner
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806040 extends AProcessor {
    private ICBannerAO cBannerAO = SpringContextHolder
        .getBean(ICBannerAO.class);

    private XN806040Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CBanner data = CBannerConverter.converter(req);
        String code = cBannerAO.addCBanner(data);
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806040Req.class);
        StringValidater.validateBlank(req.getName(), req.getPic(),
            req.getLocation(), req.getOrderNo(), req.getUrl(), req.getStatus(),
            req.getUpdater(), req.getCompanyCode());
    }
}
