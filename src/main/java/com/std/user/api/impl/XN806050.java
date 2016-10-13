package com.std.user.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.user.ao.ICBannerAO;
import com.std.user.ao.ICMaterialAO;
import com.std.user.api.AProcessor;
import com.std.user.api.converter.CBannerConverter;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.CBanner;
import com.std.user.dto.req.XN806050Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 分页查询Banner
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806050 extends AProcessor {
    private ICBannerAO cBannerAO = SpringContextHolder
        .getBean(ICBannerAO.class);

    private XN806050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CBanner condition = CBannerConverter.converter(req);
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICMaterialAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return cBannerAO.queryCBannerPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806050Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }
}
