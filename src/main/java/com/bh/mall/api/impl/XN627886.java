package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IMiniCodeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.MiniCode;
import com.bh.mall.dto.req.XN627885Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询防伪溯源
 * @author: nyc 
 * @since: 2018年7月13日 下午5:46:24 
 * @history:
 */
public class XN627886 extends AProcessor {

    private IMiniCodeAO miniCodeAO = SpringContextHolder
        .getBean(IMiniCodeAO.class);

    private XN627885Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        MiniCode condition = new MiniCode();
        condition.setStatusList(req.getStatusList());
        condition.setKeyword(req.getKeyword());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IMiniCodeAO.DEFAULT_ORDER_COLUMN;
        }

        return miniCodeAO.queryMiniCodeList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627885Req.class);
    }

}
