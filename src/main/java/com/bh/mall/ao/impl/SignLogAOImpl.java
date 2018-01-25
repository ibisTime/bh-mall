package com.bh.mall.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.ISignLogAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISignLogBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.SignLog;
import com.bh.mall.domain.User;
import com.bh.mall.dto.res.XN805100Res;
import com.bh.mall.dto.res.XN805103Res;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.exception.BizException;

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
        SYSConfig sysConfig = sysConfigBO.getConfig(
            SysConstant.CUSER_SIGN_ADDJF, user.getCompanyCode(),
            user.getSystemCode());
        if (null != sysConfig) {
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
        condition.setUserId(userId);
        condition.setSignDatetimeStart(DateUtil.getTodayStart());
        condition.setSignDatetimeEnd(DateUtil.getTodayEnd());
        if (signLogBO.getTotalCount(condition) > 0) {
            todaySign = true;
        }
        return new XN805103Res(days, todaySign);
    }
}
