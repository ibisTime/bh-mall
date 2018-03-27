package com.bh.mall.dto.req;

/**
 * 分页查询内购产品
 * @author: nyc 
 * @since: 2018年3月26日 下午3:02:17 
 * @history:
 */
public class XN627710Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1759361311074749819L;

    // （必填）名字
    private String name;

    // （必填）状态
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
