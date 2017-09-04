package com.std.user.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.user.ao.ISignLogAO;
import com.std.user.bo.IAccountBO;
import com.std.user.bo.ISYSConfigBO;
import com.std.user.bo.ISignLogBO;
import com.std.user.bo.IUserBO;
import com.std.user.bo.base.Paginable;
import com.std.user.common.AmountUtil;
import com.std.user.common.DateUtil;
import com.std.user.common.SysConstant;
import com.std.user.domain.SYSConfig;
import com.std.user.domain.SignLog;
import com.std.user.domain.User;
import com.std.user.dto.res.XN805100Res;
import com.std.user.dto.res.XN805103Res;
import com.std.user.enums.EBizType;
import com.std.user.enums.ECurrency;
import com.std.user.enums.ESysUser;
import com.std.user.enums.ESystemCode;
import com.std.user.exception.BizException;

@Service
public class SignLogAOImpl implements ISignLogAO {

    @Autowired
    private ISignLogBO signLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    protected ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public XN805100Res addSignLog(String userId, String location) {
        User user = userBO.getUser(userId);
        // 判断是否已经签到
        Boolean result = signLogBO.isSignToday(userId);
        if (result) {
            throw new BizException("XN000000", "今日已签到，请明日再来哦");
        }
        Long amount = 0L;
        if (ESystemCode.HW.getCode().equals(user.getSystemCode())) {
            SYSConfig sysConfig = sysConfigBO.getConfig(
                SysConstant.CUSER_USERREF_ADDXJK, user.getCompanyCode(),
                user.getSystemCode());
            amount = AmountUtil.mul(1000L,
                Double.valueOf(sysConfig.getCvalue()));
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_HW.getCode(),
                userId, ECurrency.JF, amount, EBizType.AJ_SIGN,
                user.getMobile() + "用户签到奖励", "签到奖励");
        }
        // 添加签到记录
        String code = signLogBO.saveSignLog(userId, location,
            user.getSystemCode());
        return new XN805100Res(code, amount);
    }

    @Override
    public Paginable<SignLog> querySignLogPage(SignLog condition, int start,
            int limit) {
        return signLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public XN805103Res getTodaySignDays(String userId) {
        Long days = signLogBO.getSerialsSignDays(userId);
        boolean todaySign = false;
        SignLog condition = new SignLog();
        condition.setSignDatetimeStart(DateUtil.getTodayStart());
        condition.setSignDatetimeEnd(DateUtil.getTodayEnd());
        if (signLogBO.getTotalCount(condition) > 0) {
            todaySign = true;
        }
        return new XN805103Res(days, todaySign);
    }
}
