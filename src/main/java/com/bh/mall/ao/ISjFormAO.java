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

    static final String DEFAULT_ORDER_COLUMN = "user_id";

    // 升级申请 627290
    public void applySjForm(String userId, String highLevel, String payPdf,
            String payAmount, String teamName, String idkind, String idNo,
            String idHand);

    // 审核升级 627292
    public void approveSjFormByB(String userId, String approver, String result,
            String remark);

    // 审核升级 627292
    public void approveSjFormByP(String userId, String approver, String result,
            String remark);

    // 分页查询 627305
    public Paginable<SjForm> querySjFormPage(int start, int limit,
            SjForm condition);

    // 列表查询 627306
    public List<SjForm> querySjFormList(SjForm condition);

    // 详细查询 627307
    public SjForm getSjForm(String userId);

}
