package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.MiniCode;

/**
 * 防伪溯源码
 * @author: LENOVO 
 * @since: 2018年8月1日 上午9:46:35 
 * @history:
 */
@Component
public interface IMiniCodeAO {

    static final String DEFAULT_ORDER_COLUMN = "security_code";

    // 分页查询
    public Paginable<MiniCode> queryMiniCodePage(int start, int limit,
            MiniCode condition);

    // 列表查询
    public List<MiniCode> queryMiniCodeList(MiniCode condition);

    // 详情查询
    public MiniCode getMiniCode(String code);

    // 防伪查询
    public int getSecurity(String miniCode);

    // 溯源查询
    public MiniCode getTrace(String traceCode);

}
