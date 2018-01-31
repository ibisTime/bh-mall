package com.bh.mall.dto.req;

/**
 * 代理设置列表查询
 * @author: chenshan 
 * @since: 2018年1月31日 上午9:53:16 
 * @history:
 */
public class XN627016Req {
    // 编号（选填）
    private String code;

    // 代理编号（选填）
    private String agentCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

}
