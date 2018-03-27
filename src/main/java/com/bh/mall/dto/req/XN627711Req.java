package com.bh.mall.dto.req;

/**
 * 列表查询内购产品
 * @author: nyc 
 * @since: 2018年3月26日 下午3:25:41 
 * @history:
 */
public class XN627711Req {
    // （选填）名称
    private String name;

    // （选填）状态
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
