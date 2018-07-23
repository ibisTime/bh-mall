package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IBarCodeDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.BarCode;

@Repository("barCodeDAOImpl")
public class BarCodeDAOImpl extends AMybatisTemplate implements IBarCodeDAO {

    @Override
    public int insert(BarCode data) {
        return super.insert(NAMESPACE.concat("insert_barCode"), data);
    }

    @Override
    public int delete(BarCode data) {
        return super.delete(NAMESPACE.concat("delete_barCode"), data);
    }

    @Override
    public BarCode select(BarCode condition) {
        return super.select(NAMESPACE.concat("select_barCode"), condition,
            BarCode.class);
    }

    @Override
    public long selectTotalCount(BarCode condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_barCode_count"),
            condition);
    }

    @Override
    public List<BarCode> selectList(BarCode condition) {
        return super.selectList(NAMESPACE.concat("select_barCode"), condition,
            BarCode.class);
    }

    @Override
    public List<BarCode> selectList(BarCode condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_barCode"), start,
            count, condition, BarCode.class);
    }

    @Override
    public void update(BarCode data) {
        super.update(NAMESPACE.concat("update_barCode"), data);
    }

    @Override
    public List<BarCode> selectCodeList(BarCode condition) {
        return super.selectList(NAMESPACE.concat("select_code"), condition,
            BarCode.class);
    }

    @Override
    public void splitSingle(BarCode data) {
        super.update(NAMESPACE.concat("split_single"), data);
    }

}
