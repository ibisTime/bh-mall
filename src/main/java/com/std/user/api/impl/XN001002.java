/**
 * @Title XN805920.java 
 * @Package com.std.user.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月22日 下午2:35:40 
 * @version V1.0   
 */
package com.std.user.api.impl;

import com.std.user.ao.IBlacklistAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN001002Req;
import com.std.user.dto.res.FlagRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 查询是否拉入黑名单
 * @author: xieyj 
 * @since: 2017年9月12日 下午3:28:35 
 * @history:
 */
public class XN001002 extends AProcessor {
    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN001002Req req = null;

    /** 
     * @see com.std.user.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return new FlagRes(blacklistAO.isBlacklist(req.getUserId(),
            req.getType()));
    }

    /** 
     * @see com.std.user.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN001002Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getType());
    }
}
