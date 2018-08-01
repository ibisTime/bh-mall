package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.domain.Address;

/**
 * 地址
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:37:14 
 * @history:
 */
public interface IAddressAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增地址
    public String addAddress(Address data);

    // 删除地址
    public int dropAddress(String code);

    // 修改地址
    public int editAddress(Address data);

    // 设置默认地址
    public int setDefaultAddress(String code);

    // 查询收件地址
    public List<Address> queryAddressList(Address condition);

    // 收件地址详情
    public Address getAddress(String code);

}
