package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.dto.req.XN627700Req;
import com.bh.mall.dto.req.XN627701Req;

/**
 * 内购产品
 * @author: LENOVO 
 * @since: 2018年7月31日 下午9:43:43 
 * @history:
 */
@Component
public interface IInnerProductAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增内购产品
    String addInnerProduct(XN627700Req req);

    // 删除内购产品
    void dropInnerProduct(String code);

    // 修改内购产品
    void editInnerProduct(XN627701Req req);

    // 分页查询内购产品
    Paginable<InnerProduct> queryInnerProductPage(int start, int limit,
            InnerProduct condition);

    // 列表查询内购产品
    List<InnerProduct> queryInnerProductList(InnerProduct condition);

    // 详情查询内购产品
    InnerProduct getInnerProduct(String code);

    // 上架内购产品
    void putOnInnerProduct(String code, String orderNo, String isFree,
            String updater);

    // 下架内购产品
    void putdownInnerProduct(String code, String updater);

    // 修改数量
    void changeQuantity(String code, String type, String quantity,
            String updater);

}
