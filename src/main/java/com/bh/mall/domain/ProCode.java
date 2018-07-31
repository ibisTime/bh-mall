package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
* 条形码（箱码）
* @author: nyc 
* @since: 2018-07-01 21:28:34
* @history:
*/
public class ProCode extends ABaseDO {

    private static final long serialVersionUID = 2812636929457896863L;

    // 码号
    private String code;

    // 状态（未使用，已使用）
    private String status;

    // 生成批次
    private String batch;

    // 生成时间
    private Date createDatetime;

    // 使用时间
    private Date useDatetime;

    // *******************db***************
    // 状态List
    private List<String> statusList;

    // 关键字
    private String keyword;

    // 防伪、溯源码
    private List<MiniCode> stList;

    // 防伪溯源URL
    private String url;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public Date getUseDatetime() {
        return useDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public void setUseDatetime(Date useDatetime) {
        this.useDatetime = useDatetime;
    }

    public List<MiniCode> getStList() {
        return stList;
    }

    public void setStList(List<MiniCode> stList) {
        this.stList = stList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
