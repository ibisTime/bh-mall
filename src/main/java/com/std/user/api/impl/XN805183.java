/**
 * @Title XN805183.java 
 * @Package com.std.user.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年5月17日 下午1:59:50 
 * @version V1.0   
 */
package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805183Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * B端用户审核（目前定制通在用，量体师审核）
 * @author: haiqingzheng 
 * @since: 2017年5月17日 下午1:59:50 
 * @history:
 */
public class XN805183 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805183Req req = null;

    /** 
     * @see com.std.user.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        userAO.approveUser(req.getUserId(), req.getApprover(),
            req.getApproveResult(), req.getDivRate(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.std.user.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805183Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getApprover(),
            req.getApproveResult());
    }

}
