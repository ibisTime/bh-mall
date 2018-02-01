package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IMaterialAO;
import com.bh.mall.bo.IMaterialBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.Material;
import com.bh.mall.dto.req.XN627030Req;
import com.bh.mall.dto.req.XN627031Req;

@Service
public class MaterialAOImpl implements IMaterialAO {

    @Autowired
    private IMaterialBO materialBO;

    @Override
    public String addMaterial(XN627030Req req) {
        Material data = new Material();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.MATERIAL.getCode());

        data.setCode(code);
        data.setLevelList(req.getLevelList());
        data.setOrderNo(req.getOrderNo());
        data.setPic(req.getPic());
        data.setStatus(req.getStatus());

        data.setTitle(req.getTitle());
        data.setType(req.getType());
        materialBO.addMaterial(data);
        return code;
    }

    @Override
    public void editMaterial(XN627031Req req) {
        materialBO.getMaterial(req.getCode());
        Material data = new Material();
        data.setCode(req.getCode());
        data.setLevelList(req.getLevelList());
        data.setOrderNo(req.getOrderNo());

        data.setPic(req.getPic());
        data.setStatus(req.getStatus());
        data.setTitle(req.getTitle());
        data.setType(req.getType());
        materialBO.editMaterial(data);
    }

    @Override
    public void dropMaterial(String code) {
        materialBO.getMaterial(code);
        materialBO.dropMaterial(code);
    }

    @Override
    public Paginable<Material> queryMaterialListPage(int start, int limit,
            Material condition) {
        return materialBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Material> queryMaterialListPage(Material condition) {
        return materialBO.queryMateroalList(condition);
    }

    @Override
    public Material getMaterial(String code) {
        return materialBO.getMaterial(code);
    }

}
