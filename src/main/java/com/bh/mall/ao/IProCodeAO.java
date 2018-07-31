package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.MiniCode;
import com.bh.mall.domain.ProCode;

/**
 * 箱码
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:07:25 
 * @history:
 */
@Component
public interface IProCodeAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增箱码
    public void addProCode(int number);

    // 分页查询
    public Paginable<ProCode> queryProCodePage(int start, int limit,
            ProCode condition);

    // 列表查询
    public List<ProCode> queryProCodeList(ProCode condition);

    // 根据code详细查询
    public ProCode getProCode(String code);

    // 取出一个未使用的箱码
    public ProCode queryProCode();

    public List<ProCode> downLoad(int number, int quantity);

    public boolean checkCode(String proCode, List<ProCode> barList,
            List<MiniCode> stList);

}
