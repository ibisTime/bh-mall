package com.bh.mall.dto.req;

/**
 * 分页查询代理授权
 * @author nyc
 *
 */
public class XN627015Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 7718579500140334339L;

    // 代理编号 （选填）
    private String agentCode;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

}
