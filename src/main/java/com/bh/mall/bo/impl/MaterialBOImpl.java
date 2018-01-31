package com.bh.mall.bo.impl;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.IMaterialBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.domain.Material;

@Component
public class MaterialBOImpl extends PaginableBOImpl<Material>
        implements IMaterialBO {

}
