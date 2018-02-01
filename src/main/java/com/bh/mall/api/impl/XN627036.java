package com.bh.mall.api.impl;

import com.bh.mall.ao.IMaterialAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.domain.Material;
import com.bh.mall.dto.req.XN627036Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 素材列表查询
 * @author: nyc 
 * @since: 2018年2月1日 上午11:56:51 
 * @history:
 */
public class XN627036 extends AProcessor {

    private IMaterialAO materilAO = SpringContextHolder
        .getBean(IMaterialAO.class);

    private XN627036Req req;

    @Override
    public Object doBusiness() throws BizException {
        Material condition = new Material();
        condition.setStatus(req.getStatus());
        condition.setTitle(req.getTitle());
        condition.setTitle(req.getType());
        return materilAO.queryMaterialListPage(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627036Req.class);
    }

}
