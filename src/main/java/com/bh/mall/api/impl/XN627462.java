/**
 * @Title XN802710.java 
 * @Package com.std.account.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年5月17日 下午8:42:43 
 * @version V1.0   
 */
package com.bh.mall.api.impl;

import com.bh.mall.ao.IWeChatAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.common.PropertiesUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627462Req;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/** 
 * 微信充值
 * @author: haiqingzheng 
 * @since: 2017年5月17日 下午8:42:43 
 * @history:
 */
public class XN627462 extends AProcessor {

    private IWeChatAO weChatAO = SpringContextHolder.getBean(IWeChatAO.class);

    private XN627462Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long transAmount = StringValidater.toLong(req.getAmount());
        if (EChannelType.WeChat_H5.getCode().equals(req.getChannelType())) {
            String payGroup = OrderNoGenerater
                .generate(EGeneratePrefix.Charge.getCode());
            return weChatAO.toPrepayIdH5(req.getApplyUser(),
                req.getAccountNumber(), payGroup, req.getApplyUser(),
                EBizType.AJ_CZ.getCode(), "微信H5支付充值", transAmount,
                PropertiesUtil.Config.WECHAT_H5_BACKURL);

        } else {
            throw new BizException("xn000000", "暂时不支持该渠道");
        }
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627462Req.class);
        ObjValidater.validateReq(req);
    }

}
