package com.std.user.api.impl;

import com.std.user.ao.ICNavigateAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN806054Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 详情查询Banner
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806054 extends AProcessor {
    private ICNavigateAO cNavigateAO = SpringContextHolder
        .getBean(ICNavigateAO.class);

    private XN806054Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return cNavigateAO.getCNavigate(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806054Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
