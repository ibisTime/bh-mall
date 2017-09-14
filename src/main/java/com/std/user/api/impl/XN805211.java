package com.std.user.api.impl;

import com.std.user.ao.IAuthLogAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805211Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 审核学生信息
 * @author: xieyj 
 * @since: 2017年9月10日 上午10:24:10 
 * @history:
 */
public class XN805211 extends AProcessor {
    private IAuthLogAO authLogAO = SpringContextHolder
        .getBean(IAuthLogAO.class);

    private XN805211Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        authLogAO.approveStudentAuth(req.getCode(), req.getApproveResult(),
            req.getApproveUser(), req.getGradDatetime(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805211Req.class);
        StringValidater.validateBlank(req.getCode(), req.getApproveResult(),
            req.getApproveUser());
    }
}
