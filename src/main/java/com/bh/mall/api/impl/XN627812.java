package com.bh.mall.api.impl;

import com.bh.mall.ao.IWareHouseAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627812Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查云仓库
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627812 extends AProcessor {

    private IWareHouseAO wareHouseAO = SpringContextHolder
        .getBean(IWareHouseAO.class);

    private XN627812Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WareHouse condition = new WareHouse();
        condition.setType(req.getType());
        condition.setKeyword(req.getKeyword());
        return wareHouseAO.queryWareHouseList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        JsonUtil.json2Bean(inputparams, XN627812Req.class);
    }

}
