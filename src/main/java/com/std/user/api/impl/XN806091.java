package com.std.user.api.impl;

import com.std.user.ao.ICMenuAO;
import com.std.user.api.AProcessor;
import com.std.user.api.converter.CMenuConverter;
import com.std.user.common.JsonUtil;
import com.std.user.domain.CMenu;
import com.std.user.dto.req.XN806091Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 列表查询菜单
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806091 extends AProcessor {
    private ICMenuAO cMenuAO = SpringContextHolder.getBean(ICMenuAO.class);

    private XN806091Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CMenu condition = CMenuConverter.converter(req);
        return cMenuAO.queryCMenuList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806091Req.class);
    }
}
