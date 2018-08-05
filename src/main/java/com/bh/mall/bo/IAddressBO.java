package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Address;

/**
 * 地址
 * @author: LENOVO 
 * @since: 2018年8月1日 上午11:31:28 
 * @history:
 */
public interface IAddressBO extends IPaginableBO<Address> {

    // 保存收件地址
    public String saveAddress(Address data);

    // 删除收件地址
    public int deleteAddress(String code);

    // 更新收件地址
    public int refreshAddress(Address data);

    // 更新收件地址默认状态
    public int refreshAddressDef(String code, String isDefault);

    // 更新收件地址默认状态
    public int refreshAddressDefByUser(String userId, String isDefault);

    // 查询收件地址列表
    public List<Address> queryAddressList(Address data);

    // 根据收件地址编号获取详细信息
    public Address getAddress(String code);

    // 是否有地址
    public boolean isHaveAddress(String userId);

    // 保存地址
    void saveAddress(String userId, String mobile, String realName,
            String province, String city, String area, String address,
            String isDefault);

    // 获取默认地址
    public Address getDefaultAddress(String userId, String code);

}
