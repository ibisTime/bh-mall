package com.bh.mall.dto.req;

/**
 * 代理授权列表查询
 * @author: chenshan 
 * @since: 2018年1月31日 上午9:53:16 
 * @history:
 */
public class XN627016Req {

    // 代理编号（选填）
    private String level;

    // 本等级是否可以被意向（选填）
    private String isIntent;

    // 是否可以被介绍（选填）
    private String isIntro;

    // 是否需要公司审核（选填）
    private String isCompanyImpower;

    // 是否需要实名（选填）
    private String isRealName;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIsIntent() {
        return isIntent;
    }

    public void setIsIntent(String isIntent) {
        this.isIntent = isIntent;
    }

    public String getIsIntro() {
        return isIntro;
    }

    public void setIsIntro(String isIntro) {
        this.isIntro = isIntro;
    }

    public String getIsCompanyImpower() {
        return isCompanyImpower;
    }

    public void setIsCompanyImpower(String isCompanyImpower) {
        this.isCompanyImpower = isCompanyImpower;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }

}
