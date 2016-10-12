package com.std.user.api.impl;

import com.std.user.ao.ICMenuAO;
import com.std.user.api.AProcessor;
import com.std.user.api.converter.CMenuConverter;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.CMenu;
import com.std.user.dto.req.XN806082Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 修改菜单
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806082 extends AProcessor {
    private ICMenuAO cMenuAO = SpringContextHolder.getBean(ICMenuAO.class);

    private XN806082Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CMenu data = CMenuConverter.converter(req);
        int count = cMenuAO.editCMenu(data);
        return new BooleanRes(count > 0 ? true : false);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806082Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getUrl(), req.getStatus(), req.getLocation(), req.getOrderNo(),
            req.getParentCode(), req.getContentType(), req.getCompanyCode());
    }
}
