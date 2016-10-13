package com.std.user.api.impl;

import com.std.user.ao.ICBannerAO;
import com.std.user.api.AProcessor;
import com.std.user.api.converter.CBannerConverter;
import com.std.user.common.JsonUtil;
import com.std.user.domain.CBanner;
import com.std.user.dto.req.XN806051Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 列表查询Banner
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806051 extends AProcessor {
    private ICBannerAO cBannerAO = SpringContextHolder
        .getBean(ICBannerAO.class);

    private XN806051Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CBanner condition = CBannerConverter.converter(req);
        return cBannerAO.queryCBannerList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806051Req.class);
    }
}
