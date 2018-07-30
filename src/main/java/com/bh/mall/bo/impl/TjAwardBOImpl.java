package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ITjAwardBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.ITjAwardDAO;
import com.bh.mall.domain.TjAward;
import com.bh.mall.dto.req.XN627548Req;
import com.bh.mall.enums.EAwardType;
import com.bh.mall.exception.BizException;

@Component
public class TjAwardBOImpl extends PaginableBOImpl<TjAward>
        implements ITjAwardBO {

    @Autowired
    private ITjAwardDAO tjAwardDAO;

    @Override
    public void saveTjAward(String code, List<XN627548Req> awardList) {

        for (XN627548Req req : awardList) {
            EAwardType.getAwardTypeMap().get(req.getType());
            TjAward data = new TjAward();
            String aCode = OrderNoGenerater
                .generate(EGeneratePrefix.Award.getCode());
            data.setCode(aCode);
            data.setProductCode(code);
            data.setType(req.getType());
            data.setLevel(StringValidater.toInteger(req.getLevel()));
            data.setValue1(StringValidater.toDouble(req.getValue1()));
            data.setValue2(StringValidater.toDouble(req.getValue2()));
            data.setValue3(StringValidater.toDouble(req.getValue3()));
            tjAwardDAO.insert(data);
        }
    }

    @Override
    public void removeTjAward(String productCode) {
        if (StringUtils.isNotBlank(productCode)) {
            TjAward data = new TjAward();
            data.setProductCode(productCode);
            List<TjAward> list = tjAwardDAO.selectList(data);
            if (list.size() > 0) {
                tjAwardDAO.delete(data);
            }
        }
    }

    @Override
    public void refreshTjAwardList(List<XN627548Req> list) {
        for (XN627548Req req : list) {
            EAwardType.getAwardTypeMap().get(req.getType());
            if (StringUtils.isBlank(req.getCode())) {
                throw new BizException("xn000", "编号不能为空");
            }
            TjAward data = this.getTjAward(req.getCode());
            data.setLevel(StringValidater.toInteger(req.getLevel()));
            data.setValue1(StringValidater.toDouble(req.getValue1()));
            data.setValue2(StringValidater.toDouble(req.getValue2()));
            data.setValue3(StringValidater.toDouble(req.getValue3()));
            tjAwardDAO.update(data);
        }
    }

    @Override
    public List<TjAward> queryTjAwardList(TjAward condition) {
        return tjAwardDAO.selectList(condition);
    }

    @Override
    public TjAward getTjAward(String code) {
        TjAward data = null;
        if (StringUtils.isNotBlank(code)) {
            TjAward condition = new TjAward();
            condition.setCode(code);
            data = tjAwardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "奖励不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshTjAward(TjAward data) {
        tjAwardDAO.update(data);
    }

    @Override
    public TjAward getTjAwardByType(Integer level, String productCode,
            String type) {
        TjAward data = null;
        if (level != 0 && StringUtils.isNotBlank(productCode)) {
            TjAward condition = new TjAward();
            condition.setLevel(level);
            condition.setProductCode(productCode);
            condition.setType(type);
            data = tjAwardDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "该产品奖励不存在");
            }
        }
        return data;
    }

    @Override
    public List<TjAward> queryTjAwardList(String type, String productCode,
            Integer level) {
        TjAward condition = new TjAward();
        condition.setType(type);
        condition.setProductCode(productCode);
        condition.setLevel(level);
        return tjAwardDAO.selectList(condition);
    }

}
