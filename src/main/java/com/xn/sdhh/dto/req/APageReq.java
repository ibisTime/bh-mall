/**
 * @Title PageReq.java 
 * @Package com.ibis.account.dto.req 
 * @Description 
 * @author miyb  
 * @date 2015-5-7 上午10:23:31 
 * @version V1.0   
 */
package com.xn.sdhh.dto.req;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: miyb 
 * @since: 2015-5-7 上午10:23:31 
 * @history:
 */
public abstract class APageReq implements Serializable {
    /** 
     * @Fields serialVersionUID :  
     */
    private static final long serialVersionUID = 9192316630188356600L;

    // 创建起始时间(YYYY-MM-DD)
    private String dateStart;

    // 创建终止时间(YYYY-MM-DD)
    private String dateEnd;

    @NotBlank(message = "页码不能为空")
    private String start;

    @NotBlank(message = "每页数量不能为空")
    private String limit;

    private String orderColumn;// 分页字段

    private String orderDir;// 分页方向

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

}
