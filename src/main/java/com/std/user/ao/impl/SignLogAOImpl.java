package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.user.ao.ISignLogAO;
import com.std.user.bo.IRuleBO;
import com.std.user.bo.ISignLogBO;
import com.std.user.bo.IUserBO;
import com.std.user.bo.base.Paginable;
import com.std.user.common.DateUtil;
import com.std.user.domain.SignLog;
import com.std.user.domain.User;
import com.std.user.dto.res.XN805100Res;
import com.std.user.enums.EBizType;
import com.std.user.enums.ERuleKind;
import com.std.user.enums.ERuleType;
import com.std.user.exception.BizException;

@Service
public class SignLogAOImpl implements ISignLogAO {

    @Autowired
    private ISignLogBO signLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IRuleBO ruleBO;

    @Override
    @Transactional
    public XN805100Res addSignLog(String userId, String location) {
        User user = userBO.getUser(userId);
        // 判断是否已经签到
        Boolean result = signLogBO.isSignToday(userId);
        if (result) {
            throw new BizException("XN000000", "今日已签到，请明日再来哦");
        }
        // 添加签到记录
        String code = signLogBO.saveSignLog(userId, location,
            user.getSystemCode());
        // 签到送钱
        Long amount = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.MRQD,
            user.getLevel());
        userBO.refreshAmount(userId, amount, code, EBizType.AJ_SR,
            ERuleType.MRQD.getValue());
        return new XN805100Res(code, amount);
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
