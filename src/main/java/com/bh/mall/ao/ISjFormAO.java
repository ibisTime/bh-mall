package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SjForm;

/**
 * 升级单
 * @author: clockorange 
 * @since: Jul 31, 2018 5:37:54 PM 
 * @history:
 */
@Component
public interface ISjFormAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 升级申请
    public void applySjForm(String userId, String highLevel, String payPdf,
            String teamName);

    // 取消升级申请
    public void cancelSjForm(String userId, String approver, String result,
            String remark);

    // 通过审核升级
    public void approveSjForm(String userId, String approver, String result,
            String remark);

    // 分页查询
    public Paginable<SjForm> querySjFormPage(int start, int limit,
            SjForm condition);

    // 列表查询
    public List<SjForm> querySjFormList(SjForm condition);

    // 详细查询
    public SjForm getSjForm(String code);

}
