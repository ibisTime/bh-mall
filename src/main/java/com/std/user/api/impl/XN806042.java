package com.std.user.api.impl;

import com.std.user.ao.ICBannerAO;
import com.std.user.api.AProcessor;
import com.std.user.api.converter.CBannerConverter;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.CBanner;
import com.std.user.dto.req.XN806042Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 修改Banner
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806042 extends AProcessor {
    private ICBannerAO cBannerAO = SpringContextHolder
        .getBean(ICBannerAO.class);

    private XN806042Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CBanner data = CBannerConverter.converter(req);
        int count = cBannerAO.editCBanner(data);
        return new BooleanRes(count > 0 ? true : false);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806042Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getPic(), req.getLocation(), req.getOrderNo(), req.getUrl(),
            req.getStatus(), req.getUpdater(), req.getCompanyCode());
    }
}
