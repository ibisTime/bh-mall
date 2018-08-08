package com.bh.mall.dto.req;

/**
 * 代理升级分页查询
 * @author nyc
 *
 */
public class XN627025Req extends APageReq {

    private static final long serialVersionUID = -8234324214267367798L;

    // 代理编号 （选填）
    private String level;

    // 本等级升级是否公司审核 （选填）
    private String isCompanyApprove;

    // 本等级升级是否余额清零 （选填）
    private String isReset;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIsCompanyApprove() {
        return isCompanyApprove;
    }

    public void setIsCompanyApprove(String isCompanyApprove) {
        this.isCompanyApprove = isCompanyApprove;
    }

    public String getIsReset() {
        return isReset;
    }

    public void setIsReset(String isReset) {
        this.isReset = isReset;
    }

}
