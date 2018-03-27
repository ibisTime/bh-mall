package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IMaterialAO;
import com.bh.mall.bo.IMaterialBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Material;
import com.bh.mall.dto.req.XN627420Req;
import com.bh.mall.dto.req.XN627421Req;

@Service
public class MaterialAOImpl implements IMaterialAO {

    @Autowired
    private IMaterialBO materialBO;

    @Override
    public String addMaterial(XN627420Req req) {
        Material data = new Material();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.MATERIAL.getCode());

        data.setCode(code);
        data.setLevel(req.getLevel());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        data.setPic(req.getPic());

        data.setStatus(req.getStatus());

        data.setTitle(req.getTitle());
        data.setType(req.getType());
        materialBO.addMaterial(data);
        return code;
    }

    @Override
    public void editMaterial(XN627421Req req) {
        Material data = materialBO.getMaterial(req.getCode());
        data.setLevel(req.getLevel());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));

        data.setPic(req.getPic());
        data.setStatus(req.getStatus());
        data.setTitle(req.getTitle());
        data.setType(req.getType());
        materialBO.editMaterial(data);
    }

    @Override
    public void dropMaterial(String code) {
        materialBO.dropMaterial(code);
    }

    @Override
    public Paginable<Material> queryMaterialListPage(int start, int limit,
            Material condition) {
        return materialBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Material> queryMaterialList(Material condition) {
        return materialBO.queryMaterialList(condition);
    }

    @Override
    public Material getMaterial(String code) {
        return materialBO.getMaterial(code);
    }

}
