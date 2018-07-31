package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Bankcard;
import com.bh.mall.dto.req.XN627520Req;
import com.bh.mall.dto.req.XN627522Req;
import com.bh.mall.dto.req.XN627523Req;

/**
 * 银行卡
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:45:36 
 * @history:
 */
public interface IBankcardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增银行卡
    public String addBankcard(XN627520Req req);

    // 删除银行卡
    public void dropBankcard(String code);

    // 修改银行卡
    public void editBankcard(XN627522Req req);

    // 修改银行卡(交易密码校验)
    public void editBankcard(XN627523Req req);

    // 分页查询
    public Paginable<Bankcard> queryBankcardPage(int start, int limit,
            Bankcard condition);

    // 列表查询
    public List<Bankcard> queryBankcardList(Bankcard condition);

    // 详情查询银行卡
    public Bankcard getBankcard(String code);

}
