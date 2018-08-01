package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Material;
import com.bh.mall.dto.req.XN627420Req;
import com.bh.mall.dto.req.XN627421Req;

/**
 * 素材
 * @author: LENOVO 
 * @since: 2018年8月1日 上午9:42:58 
 * @history:
 */
public interface IMaterialAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 新增素材
    String addMaterial(XN627420Req req);

    // 删除素材
    void dropMaterial(String code);

    // 修改素材
    void editMaterial(XN627421Req req);

    // 分页查询素材
    Paginable<Material> queryMaterialListPage(int start, int limit,
            Material condition);

    // 列表查询素材
    List<Material> queryMaterialList(Material condition);

    // 详情查询素材
    Material getMaterial(String code);

}
