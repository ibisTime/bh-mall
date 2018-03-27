package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.dto.req.XN627700Req;
import com.bh.mall.dto.req.XN627701Req;

@Component
public interface IInnerProductAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    String addInnerProduct(XN627700Req req);

    void dropInnerProduct(String code);

    void editInnerProduct(XN627701Req req);

    Paginable<InnerProduct> queryInnerProductPage(int start, int limit,
            InnerProduct condition);

    List<InnerProduct> queryInnerProductList(InnerProduct condition);

    InnerProduct getInnerProduct(String code);

    void putOnInnerProduct(String code, String orderNo, String updater);

    void putdownInnerProduct(String code, String updater);

    void changeQuantity(String code, String type, String quantity,
            String updater);

}
