package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IJourAO;
import com.bh.mall.ao.IOrderAO;
import com.bh.mall.bo.IJourBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Jour;
import com.bh.mall.enums.EAwardType;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:16:58 
 * @history:
 */
@Service
public class JourAOImpl implements IJourAO {

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IOrderAO orderAO;

    @Autowired
    private IUserBO userBO;

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
    public Jour getJour(String userId) {
        Jour data = jourBO.getJour(userId);
        boolean flag = false;
        if (EAwardType.DirectAward.getCode().equals(data.getType())) {
            flag = true;
        } else if (EAwardType.SendAward.getCode().equals(data.getType())) {
            flag = true;
        }
        // 补充关联编号的信息
        if (flag) {
            data.setUserInformation(userBO.getUser(data.getRefNo()));
        } else {
            data.setOrderInformation(orderAO.getOrder(data.getRefNo()));
        }
        return data;
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

        long count = jourBO.getTotalCount(condition);
        Page<Jour> page = new Page<Jour>(start, limit, count);
        List<Jour> list = jourBO.queryDetailPage(page.getStart(),
            page.getPageSize(), condition);
        page.setList(list);
        return page;
    }

}
