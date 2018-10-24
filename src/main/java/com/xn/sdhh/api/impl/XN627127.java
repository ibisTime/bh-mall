package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.StringValidater;
import com.xn.sdhh.dto.req.XN627127Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 获取系统用户详情
 * @author: nyc 
 * @since: 2018年7月29日 下午5:16:47 
 * @history:
 */
public class XN627127 extends AProcessor {

    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN627127Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysUserAO.getSYSUser(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627127Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
