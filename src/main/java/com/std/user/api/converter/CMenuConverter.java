package com.std.user.api.converter;

import com.std.user.core.StringValidater;
import com.std.user.domain.CMenu;
import com.std.user.dto.req.XN806080Req;
import com.std.user.dto.req.XN806082Req;
import com.std.user.dto.req.XN806090Req;
import com.std.user.dto.req.XN806091Req;

public class CMenuConverter {

    // 新增菜单
    public static CMenu converter(XN806080Req req) {
        CMenu result = new CMenu();
        result.setName(req.getName());
        result.setUrl(req.getUrl());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setParentCode(req.getParentCode());
        result.setRemark(req.getRemark());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 修改菜单
    public static CMenu converter(XN806082Req req) {
        CMenu result = new CMenu();
        result.setCode(req.getCode());
        result.setName(req.getName());
        result.setUrl(req.getUrl());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setParentCode(req.getParentCode());
        result.setRemark(req.getRemark());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 分页查询菜单
    public static CMenu converter(XN806090Req req) {
        CMenu result = new CMenu();
        result.setName(req.getName());
        result.setLocation(req.getLocation());
        result.setStatus(req.getStatus());
        result.setParentCode(req.getParentCode());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 列表查询菜单
    public static CMenu converter(XN806091Req req) {
        CMenu result = new CMenu();
        result.setName(req.getName());
        result.setLocation(req.getLocation());
        result.setStatus(req.getStatus());
        result.setParentCode(req.getParentCode());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }
}
