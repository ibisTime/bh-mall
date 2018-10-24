package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSDictAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.core.StringValidater;
import com.xn.sdhh.dto.req.XN627077Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 详情查询数据字典
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:50:23 
 * @history:
 */
public class XN627077 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN627077Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return sysDictAO.getSYSDict(StringValidater.toLong(req.getId()));
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627077Req.class);
        ObjValidater.validateReq(req);
    }
}
