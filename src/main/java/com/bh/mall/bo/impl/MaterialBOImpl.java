package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IMaterialBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IMaterialDAO;
import com.bh.mall.domain.Material;
import com.bh.mall.exception.BizException;

@Component
public class MaterialBOImpl extends PaginableBOImpl<Material> implements
        IMaterialBO {

    @Autowired
    private IMaterialDAO materialDAO;

    @Override
    public void addMaterial(Material data) {
        materialDAO.insert(data);
    }

    @Override
    public void editMaterial(Material data) {
        materialDAO.update(data);
    }

    @Override
    public void dropMaterial(String code) {
        Material condition = new Material();
        condition.setCode(code);
        materialDAO.delete(condition);
    }

    @Override
    public Material getMaterial(String code) {
        Material data = null;
        if (StringUtils.isNotBlank(code)) {
            Material condition = new Material();
            condition.setCode(code);
            data = materialDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000", "该素材编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<Material> queryMaterialList(Material condition) {
        return materialDAO.selectList(condition);
    }

}
