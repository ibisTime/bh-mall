package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IJourAO;
import com.bh.mall.bo.IJourBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Jour;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;

@Service
public class JourAOImpl implements IJourAO {

    @Autowired
    private IJourBO jourBO;

    @Override
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition) {

        return jourBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Jour> queryJourList(Jour condition) {
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }
        List<Jour> jourList = jourBO.queryJourList(condition);
        List<Jour> result = new ArrayList<Jour>();
        result.addAll(jourList);
        return result;
    }

    @Override
    public Jour getJour(String code) {
        return jourBO.getJour(code);
    }

    @Override
    public Long getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd) {
        return jourBO.getTotalAmount(bizType, channelType, accountNumber,
            dateStart, dateEnd);
    }

    @Override
    public Paginable<Jour> queryDetailPage(int start, int limit,
            Jour condition) {
        if (EBoolean.NO.getCode().equals(condition.getBizType())) {
            condition.setBizType(EBizType.AJ_TJJL.getCode());
        } else if (EBoolean.YES.getCode().equals(condition.getBizType())) {
            condition.setBizType(EBizType.AJ_CHJL.getCode());
        } else {
            condition.setBizType(EBizType.AJ_JSJL.getCode());
        }

        return jourBO.getPaginable(start, limit, condition);
    }

}
