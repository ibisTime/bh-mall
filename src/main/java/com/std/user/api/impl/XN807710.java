package com.std.user.api.impl;

import com.std.user.ao.ISYSConfigAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.SYSConfig;
import com.std.user.dto.req.XN807710Req;
import com.std.user.dto.res.PKIdRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 新增系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:51:37 
 * @history:
 */
public class XN807710 extends AProcessor {

    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN807710Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSConfig data = new SYSConfig();
        data.setToSystem("8");// 8 代表生意家 作为服务时启用该字段
        data.setCkey(req.getCkey());
        data.setCvalue(req.getCvalue());
        data.setNote(req.getNote());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        return new PKIdRes(sysConfigAO.addSYSConfig(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN807710Req.class);
        StringValidater.validateBlank(req.getCkey(), req.getCvalue(),
            req.getNote(), req.getUpdater());
    }
}
