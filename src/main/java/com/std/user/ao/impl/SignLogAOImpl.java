package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.user.ao.ISignLogAO;
import com.std.user.bo.ISignLogBO;
import com.std.user.bo.IUserBO;
import com.std.user.bo.base.Paginable;
import com.std.user.common.DateUtil;
import com.std.user.domain.SignLog;
import com.std.user.enums.EBizType;
import com.std.user.exception.BizException;

@Service
public class SignLogAOImpl implements ISignLogAO {

    @Autowired
    private ISignLogBO signLogBO;

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public String addSignLog(String userId, String location, Long amount) {
        // 判断是否已经签到
        Boolean result = signLogBO.isSignToday(userId);
        if (result) {
            throw new BizException("XN000000", "今日已签到，请明日再来");
        }
        // 添加签到记录
        String code = signLogBO.saveSignLog(userId, location);
        // 送积分
        userBO.refreshAmount(userId, amount, code, EBizType.AJ_SR, "每日签到");
        return code;
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
