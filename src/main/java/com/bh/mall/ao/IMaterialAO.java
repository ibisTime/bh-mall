package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Material;
import com.bh.mall.dto.req.XN627030Req;
import com.bh.mall.dto.req.XN627031Req;

public interface IMaterialAO {

    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加素材
     * @param req
     * @return 
     * @create: 2018年2月1日 上午11:01:40 nyc
     * @history:
     */
    public String addMaterial(XN627030Req req);

    /**
     * 修改素材
     * @param req 
     * @create: 2018年2月1日 上午11:01:53 nyc
     * @history:
     */
    public void editMaterial(XN627031Req req);

    /**
     * 删除素材
     * @param code 
     * @create: 2018年2月1日 上午11:02:11 nyc
     * @history:
     */
    public void dropMaterial(String code);

    /**
    * 素材分页查询
    * @param start
    * @param limit
    * @param condition
    * @return 
    * @create: 2018年2月1日 下午4:47:44 nyc
    * @history:
    */
    public Paginable<Material> queryMaterialListPage(int start, int limit,
            Material condition);

    /**
     * 素材列表查询
     * @param condition
     * @return 
     * @create: 2018年2月1日 下午12:00:18 nyc
     * @history:
     */
    public List<Material> queryMaterialList(Material condition);

    /**
     * 素材详情
     * @param code
     * @return 
     * @create: 2018年2月1日 下午1:48:03 nyc
     * @history:
     */
    public Material getMaterial(String code);

}
