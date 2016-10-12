package com.std.user.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.user.ao.ICMenuAO;
import com.std.user.api.AProcessor;
import com.std.user.api.converter.CMenuConverter;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.CMenu;
import com.std.user.dto.req.XN806090Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 分页查询菜单
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806090 extends AProcessor {
    private ICMenuAO cMenuAO = SpringContextHolder.getBean(ICMenuAO.class);

    private XN806090Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CMenu condition = CMenuConverter.converter(req);
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICMenuAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return cMenuAO.queryCMenuPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806090Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }
}
