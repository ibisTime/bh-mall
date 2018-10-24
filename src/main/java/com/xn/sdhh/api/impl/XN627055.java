package com.xn.sdhh.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.xn.sdhh.ao.ISYSMenuAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.core.StringValidater;
import com.xn.sdhh.domain.SYSMenu;
import com.xn.sdhh.dto.req.XN627055Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 菜单-分页查询
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:38:19 
 * @history:
 */
public class XN627055 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN627055Req req = null;

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
        req = JsonUtil.json2Bean(inputparams, XN627055Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        ObjValidater.validateReq(req);
    }
}
