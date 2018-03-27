package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Material;
import com.bh.mall.dto.req.XN627420Req;
import com.bh.mall.dto.req.XN627421Req;

public interface IMaterialAO {

    String DEFAULT_ORDER_COLUMN = "status";

    String addMaterial(XN627420Req req);

    void editMaterial(XN627421Req req);

    void dropMaterial(String code);

    Paginable<Material> queryMaterialListPage(int start, int limit,
            Material condition);

    List<Material> queryMaterialList(Material condition);

    Material getMaterial(String code);

}
