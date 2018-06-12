package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ChangeProduct;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.dto.res.XN627805Res;

@Component
public interface IChangeProductAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addChangeProduct(XN627790Req req);

    public int dropChangeProduct(String code);

    public int editChangeProduct(ChangeProduct data);

    public Paginable<ChangeProduct> queryChangeProductPage(int start, int limit,
            ChangeProduct condition);

    public List<ChangeProduct> queryChangeProductList(ChangeProduct condition);

    public ChangeProduct getChangeProduct(String code);

    public void editChangePrice(String code, String changePrice,
            String approver, String string);

    public void approveChange(String code, String aprrover, String approveNote,
            String result);

    public ChangeProduct getChangeProductMessage(XN627790Req req);

    public XN627805Res checkAmount(String userId);

}
