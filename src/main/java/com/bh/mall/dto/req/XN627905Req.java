package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询内购产品规格
 * @author: LENOVO 
 * @since: 2018年8月2日 下午4:21:23 
 * @history:
 */
public class XN627905Req extends APageReq {

    private static final long serialVersionUID = -645972324512910733L;

    // 内购产品编号
    @NotBlank
    private String innerProductCode;

    // 规格名称(选填)
    private String name;

    // 价格(选填)
    private String price;

    // 是否可拆单（选填）
    private String isSingle;

    public String getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(String isSingle) {
        this.isSingle = isSingle;
    }

    public String getInnerProductCode() {
        return innerProductCode;
    }

    public void setInnerProductCode(String innerProductCode) {
        this.innerProductCode = innerProductCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
