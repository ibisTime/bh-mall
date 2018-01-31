package com.bh.mall.dto.req;

/**
 * 代理升级分页查询
 * @author nyc
 *
 */
public class XN627025Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8234324214267367798L;

    // 代理编号 （选填）
    private String agentCode;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

}
