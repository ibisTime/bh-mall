package com.bh.mall.api.impl;

import java.util.List;

import com.bh.mall.ao.IProCodeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ProCode;
import com.bh.mall.dto.req.XN627871Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 导出防伪溯源码
 * @author: nyc 
 * @since: 2018年7月1日 下午9:22:35 
 * @history:
 */
public class XN627871 extends AProcessor {

    private IProCodeAO proCodeAO = SpringContextHolder
        .getBean(IProCodeAO.class);

    private XN627871Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        List<ProCode> list = null;
        synchronized (IProCodeAO.class) {
            list = proCodeAO.downLoad(
                StringValidater.toInteger(req.getPageNo()),
                StringValidater.toInteger(req.getPageSize()));
        }
        return list;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627871Req.class);
        ObjValidater.validateReq(req);
    }

}
