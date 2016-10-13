package com.std.user.api.converter;

import com.std.user.core.StringValidater;
import com.std.user.domain.CBanner;
import com.std.user.dto.req.XN806040Req;
import com.std.user.dto.req.XN806042Req;
import com.std.user.dto.req.XN806050Req;
import com.std.user.dto.req.XN806051Req;

public class CBannerConverter {

    // 新增Banner
    public static CBanner converter(XN806040Req req) {
        CBanner result = new CBanner();
        result.setName(req.getName());
        result.setPic(req.getPic());
        result.setLocation(req.getLocation());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setUrl(req.getUrl());
        result.setStatus(req.getStatus());
        result.setUpdater(req.getUpdater());
        result.setRemark(req.getRemark());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 修改Banner
    public static CBanner converter(XN806042Req req) {
        CBanner result = new CBanner();
        result.setCode(req.getCode());
        result.setName(req.getName());
        result.setPic(req.getPic());
        result.setLocation(req.getLocation());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setUrl(req.getUrl());
        result.setStatus(req.getStatus());
        result.setUpdater(req.getUpdater());
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
