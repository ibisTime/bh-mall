package com.bh.mall.dto.req;

import java.util.List;

/**
 * 分页查询防伪溯源
 * @author: nyc 
 * @since: 2018年7月13日 下午5:47:52 
 * @history:
 */
public class XN627885Req extends APageReq {

    private static final long serialVersionUID = -3747505680351428877L;

    // 状态
    private List<String> statusList;

    // 关键字
    private String keyword;

    // 箱码状态
    private String proStatus;

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

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

}
