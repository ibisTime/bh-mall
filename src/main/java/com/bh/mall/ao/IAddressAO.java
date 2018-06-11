package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.domain.Address;

/** 
 * @author: xieyj 
 * @since: 2015年8月27日 上午10:39:37 
 * @history:
 */
public interface IAddressAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 新增地址
     * @param data
     * @return 
     * @create: 2015年8月27日 下午2:22:02 xieyj
     * @history:
     */
    public String addAddress(Address data);

    /**
     * 删除地址
     * @param code
     * @return 
     * @create: 2015年8月27日 下午2:22:15 xieyj
     * @history:
     */
    public int dropAddress(String code);

    /**
     * 修改地址
     * @param data
     * @return 
     * @create: 2015年8月27日 下午2:22:25 xieyj
     * @history:
     */
    public int editAddress(Address data);

    /**
     * 设置默认地址
     * @param code
     * @return 
     * @create: 2016年5月23日 下午9:15:44 xieyj
     * @history:
     */
    public int setDefaultAddress(String code);

    /**
     * 查询收件地址
     * @param condition
     * @return 
     * @create: 2015年8月27日 下午2:22:56 xieyj
     * @history:
     */
    public List<Address> queryAddressList(Address condition);

    /**
     * 收件地址详情
     * @param code
     * @return 
     * @create: 2016年5月24日 下午3:13:59 xieyj
     * @history:
     */
    public Address getAddress(String code);

}
