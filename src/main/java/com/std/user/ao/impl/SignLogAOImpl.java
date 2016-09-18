package com.std.user.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ISignLogAO;
import com.std.user.bo.ISignLogBO;
import com.std.user.bo.base.Paginable;
import com.std.user.common.DateUtil;
import com.std.user.core.OrderNoGenerater;
import com.std.user.domain.SignLog;

@Service
public class SignLogAOImpl implements ISignLogAO {

    @Autowired
    private ISignLogBO signLogBO;

    @Override
    public String saveSignLog(String userId, String location) {
        SignLog data = new SignLog();
        if (userId != null && userId != "") {
            String code = OrderNoGenerater.generate("QD");
            data.setCode(code);
            data.setUserId(userId);
            data.setLocation(location);
            data.setSignDatetime(new Date());
            signLogBO.saveSignLog(data);
        }
        return data.getCode();
    }

    @Override
    public List<SignLog> querySignLogList(String userId,
            String signDatetimeStart, String signDatetimeEnd) {
        SignLog condition = new SignLog();
        condition.setUserId(userId);
        condition.setSignDatetimeStart(DateUtil.strToDate(signDatetimeStart,
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setSignDatetimeEnd(DateUtil.strToDate(signDatetimeEnd,
            DateUtil.DATA_TIME_PATTERN_1));
        return signLogBO.querySignLogList(condition);
    }

    @Override
    public Paginable<SignLog> querySignLogPage(SignLog condition, int start,
            int limit) {
        return signLogBO.getPaginable(start, limit, condition);
    }
}
