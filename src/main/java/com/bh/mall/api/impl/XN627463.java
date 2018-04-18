/**
 * @Title XN802710.java 
 * @Package com.std.account.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年5月17日 下午8:42:43 
 * @version V1.0   
 */
package com.bh.mall.api.impl;

import com.bh.mall.ao.IChargeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627463Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/** 
 * 审核线下充值
 * @author: haiqingzheng 
 * @since: 2017年5月17日 下午8:42:43 
 * @history:
 */
public class XN627463 extends AProcessor {

    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN627463Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        chargeAO.payOrder(req.getCode(), req.getPayUser(), req.getPayResult(),
            req.getPayNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627463Req.class);
        ObjValidater.validateReq(req);
    }

}
