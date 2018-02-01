package com.bh.mall.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IMaterialBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.dao.IMaterialDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.Material;
import com.bh.mall.exception.BizException;

@Component
public class MaterialBOImpl extends PaginableBOImpl<Material>
        implements IMaterialBO {

    @Autowired
    private IMaterialDAO materialDAO;

    @Autowired
    private IAgentDAO agentDAO;

    @Override
    public void addMaterial(Material data) {
        String[] listLevel = data.getLevelList().split(",");
        for (String level : listLevel) {
            Agent condition = new Agent();
            condition.setLevel(level);
            Agent agentData = agentDAO.selectByLevel(condition);
            if (agentData == null) {
                throw new BizException("xn000", "该代理等级不存在");
            }
        }
        materialDAO.insert(data);
    }

    @Override
    public void editMaterial(Material data) {
        String[] levelList = data.getLevelList().split(",");
        for (String level : levelList) {
            Agent condition = new Agent();
            condition.setLevel(level);
            Agent angeData = agentDAO.select(condition);
            if (angeData == null) {
                throw new BizException("xn000", "该代理等级不存在");
            }
        }
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
        Material condition = new Material();
        condition.setCode(code);
        Material data = materialDAO.select(condition);
        if (data == null) {
            throw new BizException("xn000", "该素材编号不存在");
        }
        return data;
    }

    @Override
    public List<Material> selectMateroalList(Material condition) {
        return materialDAO.selectList(condition);
    }

}
