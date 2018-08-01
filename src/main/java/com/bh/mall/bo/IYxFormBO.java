
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.YxForm;

/**
 * 意向单
 * @author: clockorange 
 * @since: Aug 1, 2018 11:38:44 AM 
 * @history:
 */
public interface IYxFormBO extends IPaginableBO<YxForm> {

    // 代理申请
    public String applyYxForm(YxForm data);

    // 意向分配
    public String allotYxForm(YxForm data);

    // 接受意向分配
    public String accepYxForm(YxForm data);

    // 忽略意向分配
    public String ignore(YxForm data);

    // 保存意向分配
    public String saveYxForm(YxForm data, String toUserId);

    // 通过等级查询
    public YxForm getYxFormByLevel(Integer integer);

    // 列表查询
    public List<YxForm> queryYxFormList(YxForm condition);

    // 详细查询
    public YxForm getYxForm(String code);

    // 分页查询
    public List<YxForm> queryYxFormPage(int start, int limit, YxForm condition);

}
