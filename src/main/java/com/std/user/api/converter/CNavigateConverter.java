package com.std.user.api.converter;

import com.std.user.core.StringValidater;
import com.std.user.domain.CNavigate;
import com.std.user.dto.req.XN806040Req;
import com.std.user.dto.req.XN806042Req;
import com.std.user.dto.req.XN806043Req;
import com.std.user.dto.req.XN806050Req;
import com.std.user.dto.req.XN806051Req;
import com.std.user.dto.req.XN806052Req;
import com.std.user.dto.req.XN806053Req;

public class CNavigateConverter {

    // 新增导航
    public static CNavigate converter(XN806040Req req) {
        CNavigate result = new CNavigate();
        result.setName(req.getName());
        result.setType(req.getType());
        result.setUrl(req.getUrl());
        result.setPic(req.getPic());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setRemark(req.getRemark());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 修改导航
    public static CNavigate converter(XN806042Req req) {
        CNavigate result = new CNavigate();
        result.setCode(req.getCode());
        result.setName(req.getName());
        result.setType(req.getType());
        result.setUrl(req.getUrl());
        result.setPic(req.getPic());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setRemark(req.getRemark());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 修改城市网导航
    public static CNavigate converter(XN806043Req req) {
        CNavigate result = new CNavigate();
        result.setCode(req.getCode());
        result.setName(req.getName());
        result.setUrl(req.getUrl());
        result.setPic(req.getPic());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setBelong(req.getBelong());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 分页查询城市网导航
    public static CNavigate converter(XN806050Req req) {
        CNavigate result = new CNavigate();
        result.setName(req.getName());
        result.setStatus(req.getStatus());
        result.setType(req.getType());
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 分页查询导航
    public static CNavigate converter(XN806051Req req) {
        CNavigate result = new CNavigate();
        result.setName(req.getName());
        result.setType(req.getType());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 列表查询城市网导航
    public static CNavigate converter(XN806052Req req) {
        CNavigate result = new CNavigate();
        result.setName(req.getName());
        result.setStatus(req.getStatus());
        result.setType(req.getType());
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 列表查询导航
    public static CNavigate converter(XN806053Req req) {
        CNavigate result = new CNavigate();
        result.setName(req.getName());
        result.setType(req.getType());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }
}
