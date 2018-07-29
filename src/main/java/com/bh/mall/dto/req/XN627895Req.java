package com.bh.mall.dto.req;

import java.util.List;

public class XN627895Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -3747505680351428877L;

    // 状态
    private List<String> statusList;

    // 关键字
    private String keyword;

    public List<String> getStatusList() {
        return statusList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
