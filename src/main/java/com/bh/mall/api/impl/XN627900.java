package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ISYSMenuAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SYSMenu;
import com.bh.mall.dto.req.XN627900Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 菜单-分页查询
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:38:19 
 * @history:
 */
public class XN627900 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN627900Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu condition = new SYSMenu();
        condition.setNameForQuery(req.getName());
        condition.setType(req.getType());
        condition.setParentCode(req.getParentCode());
        condition.setUpdater(req.getUpdater());
        condition.setSystemCode(req.getSystemCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSMenuAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return sysMenuAO.querySYSMenuPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627900Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getSystemCode());
    }
}
