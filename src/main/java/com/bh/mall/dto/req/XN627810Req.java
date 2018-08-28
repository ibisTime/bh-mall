package com.bh.mall.dto.req;

/**
 * 分页查询云仓产品
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627810Req extends APageReq {

    private static final long serialVersionUID = 7257435264619052347L;

    // 编号（选填）
    private String code;

    // 用户名称 （选填）
    private String keyword;

    // 类型 （选填）
    private String type;

    // 等级（选填）
    private String level;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getLevel() {
        return level;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
