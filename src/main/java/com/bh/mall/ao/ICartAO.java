package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.Product;

/**
 * 购物车
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:50:59 
 * @history:
 */
@Component
public interface ICartAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 批量删除
    void dropCart(List<String> list);

    // 分页查询
    public Paginable<Cart> queryCartPage(int start, int limit, Cart condition);

    // 列表查询
    List<Cart> queryCartList(Cart condition);

    // 根据编号查询
    Cart getCart(String code);

    // 添加购物车
    String addCart(String userId, String level, String specsCode,
            String quantity);

    // 修改产品数量
    void editCart(String code, String quantity);

    // 根据产品规格编号
    Product getCartProduct(String specsCode);

}
