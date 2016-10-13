package com.std.user.api.converter;

import com.std.user.domain.CBanner;
import com.std.user.domain.CPassword;
import com.std.user.dto.req.XN806020Req;
import com.std.user.dto.req.XN806022Req;
import com.std.user.dto.req.XN806050Req;
import com.std.user.dto.req.XN806051Req;

public class CPasswordConverter {

    // 新增密码记录
    public static CPassword converter(XN806020Req req) {
        CPassword result = new CPassword();
        result.setType(req.getType());
        result.setAccount(req.getAccount());
        result.setPassword(req.getPassword());
        result.setRemark(req.getRemark());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 修改密码记录
    public static CPassword converter(XN806022Req req) {
        CPassword result = new CPassword();
        result.setCode(req.getCode());
        result.setType(req.getType());
        result.setAccount(req.getAccount());
        result.setPassword(req.getPassword());
        result.setRemark(req.getRemark());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 分页查询Banner
    public static CBanner converter(XN806050Req req) {
        CBanner result = new CBanner();
        result.setName(req.getName());
        result.setLocation(req.getLocation());
        result.setStatus(req.getStatus());
        result.setUpdater(req.getUpdater());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 列表查询Banner
    public static CBanner converter(XN806051Req req) {
        CBanner result = new CBanner();
        result.setName(req.getName());
        result.setLocation(req.getLocation());
        result.setStatus(req.getStatus());
        result.setUpdater(req.getUpdater());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }
}
