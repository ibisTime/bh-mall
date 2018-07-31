package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Charge;

public interface IChargeDAO extends IBaseDAO<Charge> {
    String NAMESPACE = IChargeDAO.class.getName().concat(".");

    /**
     * 更新充值订单
     * @param data 
     * @create: 2018年7月31日 下午2:29:36 LENOVO
     * @history:
     */
    void payOrder(Charge data);

    /**
     * 统计充值订单总量
     * @param condition
     * @return 
     * @create: 2018年7月31日 下午2:30:17 LENOVO
     * @history:
     */
    long getFrontTotalCount(Charge condition);

    /**
     * 分页查充值订单
     * @param pageNO
     * @param pageSize
     * @param condition
     * @return 
     * @create: 2018年7月31日 下午2:30:54 LENOVO
     * @history:
     */
    List<Charge> selectFrontChargePage(int pageNO, int pageSize,
            Charge condition);
}
