package com.bh.mall.dto.req;


/**
 * 列表查询介绍奖励
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:33:35 
 * @history:
 */
public class XN627246Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -1602819369609298450L;

    // 编号
    private String code;

    // 等级
    private String level;

    // 更新人
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
