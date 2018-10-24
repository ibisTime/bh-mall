package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSDictAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627070Req;
import com.xn.sdhh.dto.res.PKIdRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 新增数据字典
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:45:23 
 * @history:
 */
public class XN627070 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN627070Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return new PKIdRes(sysDictAO.addSYSDict(req.getType(),
            req.getParentKey(), req.getDkey(), req.getDvalue(),
            req.getUpdater(), req.getRemark()));
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627070Req.class);
        ObjValidater.validateReq(req);
    }
}
