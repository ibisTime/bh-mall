package com.std.user.api.impl;

import com.std.user.ao.IUserExtAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.domain.UserExt;
import com.std.user.dto.req.XN805060Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 查询辖区的用户列表
 * @author: xieyj 
 * @since: 2016年12月27日 下午9:01:00 
 * @history:
 */
public class XN805060 extends AProcessor {

    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN805060Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        UserExt condition = new UserExt();
        condition.setProvinceForQuery(req.getProvince());
        condition.setCityForQuery(req.getCity());
        condition.setAreaForQuery(req.getArea());
        condition.setKind(req.getKind());
        condition.setRoleCode(req.getRoleCode());
        condition.setStatus(req.getStatus());
        return userExtAO.queryUserExtList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805060Req.class);
    }
}
