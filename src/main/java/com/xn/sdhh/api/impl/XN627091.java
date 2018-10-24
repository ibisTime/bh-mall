package com.xn.sdhh.api.impl;

import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627091Req;
import com.xn.sdhh.dto.res.XN805951Res;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;
import com.xn.sdhh.third.qiniu.impl.QnTokenImpl;

/**
 * 根据系统编号获取七牛uploadToken
 * @author: xieyj 
 * @since: 2016年10月11日 上午9:45:51 
 * @history:
 */
public class XN627091 extends AProcessor {
    private QnTokenImpl qnTokenImpl = SpringContextHolder
        .getBean(QnTokenImpl.class);

    private XN627091Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805951Res(qnTokenImpl.getUploadToken(req.getCompanyCode(),
            req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627091Req.class);
        ObjValidater.validateReq(req);
    }
}
