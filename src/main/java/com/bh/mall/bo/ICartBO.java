package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Cart;

/**
 * 购物车
 * @author: LENOVO 
 * @since: 2018年8月1日 下午2:29:47 
 * @history:
 */
public interface ICartBO extends IPaginableBO<Cart> {

    // 新增购物车
    public void saveCart(Cart data);

    // 删除购物车
    public void removeCart(Cart data);

    // 更新购物车
    public void refreshCart(Cart data);

    // 列表查询购物车
    public List<Cart> queryCartList(Cart condition);

    // 详情查购物车
    public Cart getCart(String code);

    // 根据产品编号和产品规格编号查询
    public Cart getCartByProductCode(String userId, String specsCode);

}
