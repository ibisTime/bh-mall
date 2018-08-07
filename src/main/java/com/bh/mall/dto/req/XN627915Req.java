package com.bh.mall.dto.req;

/**
 * 分页查询云仓订单
 * @author: LENOVO 
 * @since: 2018年8月2日 下午4:21:23 
 * @history:
 */
public class XN627915Req extends APageReq {

    private static final long serialVersionUID = -645972324512910733L;

    // （选填）关键字
    private String keyword;

    // （选填）状态
    private String status;

    public String getKeyword() {
        return keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
