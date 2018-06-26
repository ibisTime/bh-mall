package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAwardBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IAwardDAO;
import com.bh.mall.domain.Award;
import com.bh.mall.dto.req.XN627548Req;
import com.bh.mall.enums.EAwardType;
import com.bh.mall.exception.BizException;

@Component
public class AwardBOImpl extends PaginableBOImpl<Award> implements IAwardBO {

    @Autowired
    private IAwardDAO awardDAO;

    @Override
    public void saveAward(String code, List<XN627548Req> awardList) {

        for (XN627548Req req : awardList) {
            EAwardType.getAwardTypeMap().get(req.getType());
            Award data = new Award();
            String aCode = OrderNoGenerater
                .generate(EGeneratePrefix.Award.getCode());
            data.setCode(aCode);
            data.setProductCode(code);
            data.setType(req.getType());
            data.setLevel(StringValidater.toInteger(req.getLevel()));
            data.setValue1(StringValidater.toDouble(req.getValue1()));
            data.setValue2(StringValidater.toDouble(req.getValue2()));
            data.setValue3(StringValidater.toDouble(req.getValue3()));
            awardDAO.insert(data);
        }
    }

    @Override
    public void removeAward(String productCode) {
        if (StringUtils.isNotBlank(productCode)) {
            Award data = new Award();
            data.setProductCode(productCode);
            List<Award> list = awardDAO.selectList(data);
            if (list.size() > 0) {
                awardDAO.delete(data);
            }
        }
    }

    @Override
    public void refreshAwardList(List<XN627548Req> list) {
        for (XN627548Req req : list) {
            EAwardType.getAwardTypeMap().get(req.getType());
            if (StringUtils.isBlank(req.getCode())) {
                throw new BizException("xn000", "编号不能为空");
            }
            Award data = this.getAward(req.getCode());
            data.setLevel(StringValidater.toInteger(req.getLevel()));
            data.setValue1(StringValidater.toDouble(req.getValue1()));
            data.setValue2(StringValidater.toDouble(req.getValue2()));
            data.setValue3(StringValidater.toDouble(req.getValue3()));
            awardDAO.update(data);
        }
    }

    @Override
    public List<Award> queryAwardList(Award condition) {
        return awardDAO.selectList(condition);
    }

    @Override
    public Award getAward(String code) {
        Award data = null;
        if (StringUtils.isNotBlank(code)) {
            Award condition = new Award();
            condition.setCode(code);
            data = awardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "奖励不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshAward(Award data) {
        awardDAO.update(data);
    }

    @Override
    public Award getAwardByType(Integer level, String productCode,
            String type) {
        Award data = null;
        if (level != 0 && StringUtils.isNotBlank(productCode)) {
            Award condition = new Award();
            condition.setLevel(level);
            condition.setProductCode(productCode);
            condition.setType(type);
            data = awardDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "该产品奖励不存在");
            }
        }
        return data;
    }

    @Override
    public List<Award> queryAwardList(String type, String productCode,
            Integer level) {
        Award condition = new Award();
        condition.setType(type);
        condition.setProductCode(productCode);
        condition.setLevel(level);
        return awardDAO.selectList(condition);
    }

}
