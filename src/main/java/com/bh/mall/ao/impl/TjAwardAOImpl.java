package com.bh.mall.ao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ITjAwardAO;
import com.bh.mall.bo.ITjAwardBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.TjAward;

@Service
public class TjAwardAOImpl implements ITjAwardAO {

    @Autowired
    private ITjAwardBO tjAwardBO;

    @Override
    public Paginable<TjAward> queryAwardPage(int start, int limit,
            TjAward condition) {

        Paginable<TjAward> page = tjAwardBO.getPaginable(start, limit,
            condition);
        for (Iterator<TjAward> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            TjAward award = iterator.next();
            if (award.getLevel() == 6) {
                iterator.remove();
            }

        }
        return page;
    }

    @Override
    public List<TjAward> queryAwardList(TjAward condition) {
        List<TjAward> list = tjAwardBO.queryAwardList(condition);
        for (Iterator<TjAward> iterator = list.iterator(); iterator
            .hasNext();) {
            TjAward award = iterator.next();
            if (award.getLevel() == 6) {
                iterator.remove();
            }
        }
        return list;
    }

    @Override
    public TjAward getAward(String code) {
        return tjAwardBO.getAward(code);
    }

    @Override
    public void editAward(String code, String value1, String value2,
            String value3) {
        TjAward data = tjAwardBO.getAward(code);
        data.setValue1(StringValidater.toDouble(value1));
        data.setValue2(StringValidater.toDouble(value2));
        data.setValue3(StringValidater.toDouble(value3));
        tjAwardBO.refreshAward(data);
    }

}
