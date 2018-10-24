package com.xn.sdhh.dto.req;

import java.util.List;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午4:09:39 
 * @history:
 * 分页查询系统参数
 */
public class XN627085Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 类型(选填)
    private String type;

    // 类型(选填)
    private List<String> typeList;

    // key值(选填)
    private String ckey;

    // 更新人（选填）
    private String updater;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

}
