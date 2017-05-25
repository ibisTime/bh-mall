/**
 * @Title XN805184.java 
 * @Package com.std.user.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年5月25日 上午10:35:36 
 * @version V1.0   
 */
package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805184Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 修改分配比例
 * @author: haiqingzheng 
 * @since: 2017年5月25日 上午10:35:36 
 * @history:
 */
public class XN805184 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805184Req req = null;

    /** 
     * @see com.std.user.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        userAO.doEditDivRate(req.getUserId(),
            StringValidater.toDouble(req.getDivRate()));
        return new BooleanRes(true);
    }

    /** 
     * @see com.std.user.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805184Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getDivRate());
    }

}
