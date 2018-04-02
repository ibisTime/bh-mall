package com.bh.mall.dto.req;

import java.util.List;

/**
 * 批量删除
 * @author: nyc 
 * @since: 2018年3月27日 下午2:03:38 
 * @history:
 */
public class XN627622Req {

    // 编号
    private List<String> codeList;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

}
