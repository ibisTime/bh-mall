package com.bh.mall.dto.req;

/**
 * 分页查询内购产品
 * @author: nyc 
 * @since: 2018年3月26日 下午3:02:17 
 * @history:
 */
public class XN627710Req extends APageReq {

    private static final long serialVersionUID = 1759361311074749819L;

    // （必填）编号
    private String code;

    // （必填）名字
    private String name;

    // （必填）状态
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
