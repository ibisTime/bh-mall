package com.bh.mall.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.bh.mall.domain.Cart;

/**
 * 批量删除
 * @author: nyc 
 * @since: 2018年3月27日 下午2:03:38 
 * @history:
 */
public class XN627622Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private List<Cart> codeList;

    public List<Cart> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<Cart> codeList) {
        this.codeList = codeList;
    }

}
